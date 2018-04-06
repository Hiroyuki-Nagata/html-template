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

(defn getfield
  [this key]
  (@(.state this) key))

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
    (string? param) (getfield this param)
    (map? param) (reduce-kv (fn [m k v] (setfield this k v)) {} param)
    :else nil))

(defn replace-several [content & replacements]
  (let [replacement-list (partition 2 replacements)]
    (reduce #(apply clojure.string/replace %1 %2) content replacement-list)))

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
                                      content (if (= 1 (count raw-content))
                                                (first raw-content)
                                                (list* raw-content))
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
          hatena (second (second hiccup-expr))]
      (clojure.pprint/pprint raw-str)
      (clojure.pprint/pprint s-expr)
      (clojure.pprint/pprint hiccup-expr)
      (clojure.pprint/pprint hatena)
      (html hatena))))
