(ns html-template.core
  (:gen-class)

  (:import [org.antlr.v4.runtime ANTLRInputStream CommonTokenStream]
           [html_template.core HTMLParser HTMLLexer])

  (:require [clojure.java.io :as io]))

(defn stream [input]
  (new ANTLRInputStream input))

(defn html-parser [tokens]
  (new HTMLParser tokens))

(defn html-lexer [stream]
  (new HTMLLexer stream))

(defn tokens [lexer]
  (new CommonTokenStream lexer))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [stream (stream "<html>Hello,World</html>")
        lexer (html-lexer stream)
        tok (tokens lexer)
        parser (html-parser tok)
        ctx (.htmlDocument parser)]
    (println (.toStringTree ctx))))
