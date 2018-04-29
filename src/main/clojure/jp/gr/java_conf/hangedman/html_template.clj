(ns jp.gr.java_conf.hangedman.html_template
  (:require
   [instaparse.core]
   [clojure.java.io :as io]
   [clojure.zip :as zipper]
   [jp.gr.java_conf.hangedman.html_parser_simple_listener])

  (:gen-class :name jp.gr.java_conf.hangedman.HtmlTemplate
              :init init
              :state state
              :constructors {[clojure.lang.PersistentArrayMap] []}
              :methods [[param [String] String]
                        [param [clojure.lang.PersistentArrayMap] void]
                        [output [] String]])

  (:import
   [clojure.lang PersistentArrayMap]
   [org.antlr.v4.runtime ANTLRInputStream]
   [org.antlr.v4.runtime CommonTokenStream]
   [org.antlr.v4.runtime.tree ParseTreeWalker]
   [jp.gr.java_conf.hangedman.html_template HTMLParser]
   [jp.gr.java_conf.hangedman.html_template HTMLLexer]
   [jp.gr.java_conf.hangedman HtmlParserSimpleListener])

  (:use
   [hiccup.core]
   [plumbing.core]
   [clojure.tools.logging]))

(defn setfield
  [this key value]
  (swap! (.state this) into {key value}))

;; (swap! ppl assoc-in ["persons" "bob"] {:age 11})

(defn set-nestedfield
  [this keys m]
  (swap! (.state this) assoc-in keys m))

(defn getfield
  [this key]
  (@(.state this) key))

(defn get-nestedfield
  [this keys]
  (get-in @(.state this) keys))

(defn stream [input]
  (new ANTLRInputStream input))

(defn html-parser [tokens]
  (new HTMLParser tokens))

(defn html-lexer [stream]
  (new HTMLLexer stream))

(defn tokens [lexer]
  (new CommonTokenStream lexer))

(defn walker []
  (new ParseTreeWalker))

(defn listener []
  (new HtmlParserSimpleListener))

(defn parse [elements]
  (let [stream (stream elements)
        lexer (html-lexer stream)
        tok (tokens lexer)
        parser (html-parser tok)
        ctx (.htmlDocument parser)]
    {:stream stream :lexer lexer :token tok :parser parser :ctx ctx}))

;;
;; ctor
;;
(defnk -init [filename {option nil} {path nil} {utf8 nil}]
  (debug (str "TEMPLATE: \n" (str filename)))
  (let [contents (slurp (io/resource filename))]
    (debug contents)
    [[] (atom (parse contents))]))

;;
;; param
;;
(defn -param [this param]
  (debug (type param))
  (cond
    (string? param) (get-nestedfield this [:params param])
    (map? param) (reduce-kv (fn [m k v] (set-nestedfield this [:params k] v)) {} param)
    :else nil))

(defn replace-several [content & replacements]
  (let [replacement-list (partition 2 replacements)]
    (reduce #(apply clojure.string/replace %1 %2) content replacement-list)))

(defn filter-vec-keys [key contents]
  (let [ans (filter
             (fn [e] (and
                      (vector? e)
                      (= key (first e)))) contents)]
  ;; return only vector values
  (map (fn [v] (second v)) ans)))

(defn make-kvmaps-from-list [name-list val-list]
  (map hash-map
       (map (fn [e] (keyword e)) name-list)
       (map (fn [e] (str e)) val-list)))

(defn make-hash-or-lazyseq [attr-seq]
  (let [possible-name (nth attr-seq 0 nil)
        possible-val  (nth attr-seq 2 nil)]

    (if (and (= :htmlAttributeName (first possible-name))
             (= :htmlAttributeValue (first possible-val)))
      ;; replace attributes !
      (do
        ;; (println (str "*** lazyseq? ***"))
        ;; (clojure.pprint/pprint possible-name)
        ;; (clojure.pprint/pprint possible-val)
        ;; (println (str "*** lazyseq! ***"))
        {(keyword (second possible-name)) (second possible-val)})
      ;; do nothing !
      attr-seq)))

(defn replace-attr-from-vec [tmp-content attr-map]
  ;; (debug (str "type? " (type tmp-content)))
  (if (seq? tmp-content)
    ;; Get "clojure.lang.PersistentVector$ChunkedSeq"
    (let [replaced-content (map
                            (fn [e] (if (and (vector? e)
                                             (seq? (nth e 1 nil)))
                                      ;; vector!
                                      (do
                                        (let [tag (nth e 0 nil)
                                              attr (nth e 1 nil)
                                              new-attr (make-hash-or-lazyseq attr)]
                                        ;; (println "--- START ---")
                                        ;; (println (type e))
                                        ;; (clojure.pprint/pprint (str "element type: " e))
                                        ;; (clojure.pprint/pprint (str "[0]: " tag))
                                        ;; (clojure.pprint/pprint (str "[1]: " attr))
                                        ;; (println "--- TRANSFORMED ---")
                                        ;; (clojure.pprint/pprint (vector tag new-attr))
                                        ;; (println "---  END ---")
                                        ;; update
                                        (vector tag new-attr)))
                                      ;; else
                                      e))
                            tmp-content)]
    replaced-content)
    ;; Other type
    tmp-content))

(defn instaparse-transform [tree]
  (instaparse.core/transform
   {
    :htmlChardata (fn [& args] (apply clojure.string/join " " (list args)))
    :htmlTagName (fn [& args] (apply vector args))

    ;; Get :htmlElement vector first value as HTML tag
    ;; Get :htmlElement vector second value as HTML contents
    ;; Remove :htmlContent vectors after processed above
    :htmlElement (fn [& args] (do
                                (let [tag (keyword (ffirst args))
                                      raw-content (drop 1 (second args))
                                      ;; Get attribute name/values
                                      attr-names (filter-vec-keys :htmlAttributeName raw-content)
                                      attr-vals (filter-vec-keys :htmlAttributeValue raw-content)
                                      attr-map (make-kvmaps-from-list attr-names attr-vals)
                                      ;; Remove :htmlContent vectors
                                      tmp-content (if (= 1 (count raw-content))
                                                    (first raw-content)
                                                    (list* raw-content))
                                      ;; Replace attributes with attr-maps
                                      content (replace-attr-from-vec tmp-content attr-map)
                                      ans (vector tag content)]

                                  ans)))
    }
   tree))

;;
;; output
;;
(defn -output [this]
  (let [ctx (getfield this :ctx)
        parser (getfield this :parser)]

    ;; convert s-expr
    (let [raw-str (replace-several
                   (.toStringTree ctx parser)
                   #"html([a-zA-Z]+)" ":html$1"
                   #"\\n" ""
                   #"<" ""
                   #">" ""
                   #"/" ""
                   #"\(" "["
                   #"\)" "]")
          s-expr (read-string raw-str)
          hiccup-expr (instaparse-transform s-expr)
          params @(.state this)
          hatena (second (second hiccup-expr))]
      ;; (clojure.pprint/pprint raw-str)
      ;; (clojure.pprint/pprint s-expr)
      ;; (clojure.pprint/pprint hiccup-expr)
      ;; (clojure.pprint/pprint hatena)

      (clojure.pprint/pprint params)
      (html hatena))))
