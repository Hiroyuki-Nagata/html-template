(ns jp.gr.java_conf.hangedman.html_template
  (:gen-class :name jp.gr.java_conf.hangedman.HtmlTemplate
              :init init
              :state state
              :constructors {[clojure.lang.PersistentArrayMap] []})

  (:import [org.antlr.v4.runtime ANTLRInputStream]
           [org.antlr.v4.runtime CommonTokenStream]
           [jp.gr.java_conf.hangedman.html_template HTMLParser]
           [jp.gr.java_conf.hangedman.html_template HTMLLexer])

  (:use [plumbing.core])

  (:require [clojure.java.io :as io]))

(defnk -init [filename])

(defn -toString [this] "Hello")

(defn stream [input]
  (new ANTLRInputStream input))

(defn html-parser [tokens]
  (new HTMLParser tokens))

(defn html-lexer [stream]
  (new HTMLLexer stream))

(defn tokens [lexer]
  (new CommonTokenStream lexer))

;; (defn -main
;;   "I don't do a whole lot ... yet."
;;   [& args]
;;   (let [stream (stream "<html>Hello,World</html>")
;;         lexer (html-lexer stream)
;;         tok (tokens lexer)
;;         parser (html-parser tok)
;;         ctx (.htmlDocument parser)]
;;     (println (.toStringTree ctx))))
