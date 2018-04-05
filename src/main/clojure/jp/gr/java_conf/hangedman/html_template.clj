(ns jp.gr.java_conf.hangedman.html_template
  (:require
   [clojure.java.io :as io]
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
   [jp.gr.java_conf.hangedman.html_template HTMLParser]
   [jp.gr.java_conf.hangedman.html_template HTMLLexer]
   [jp.gr.java_conf.hangedman HtmlParserSimpleListener])

  (:use
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

(defn parse [elements]
  (let [stream (stream elements)
        lexer (html-lexer stream)
        tok (tokens lexer)
        parser (html-parser tok)
        ctx (.htmlDocument parser)]
    (debug (.toStringTree ctx))))

;;
;; ctor
;;
(defnk -init [filename {option nil} {path nil} {utf8 nil}]
  (debug (str "TEMPLATE: \n" (str filename)))
  (let [contents (slurp (io/resource filename))]
    (debug contents)
    (parse contents))
  [[] (atom {})])

;;
;; param
;;
(defn -param [this param]
  (debug (type param))
  (cond
    (string? param) (getfield this param)
    (map? param) (reduce-kv (fn [m k v] (setfield this k v)) {} param)
    :else nil))

;;
;; output
;;
(defn -output [this]
  "")

;; -------------------------------------------------------------------------------
