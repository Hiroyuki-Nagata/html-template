(ns jp.gr.java_conf.hangedman.html_parser_simple_listener
  (:require [clojure.zip :as zipper])

  (:import
   [org.antlr.v4.runtime ParserRuleContext]
   [org.antlr.v4.runtime.misc NotNull]
   [org.antlr.v4.runtime.tree ErrorNode]
   [org.antlr.v4.runtime.tree TerminalNode]
   [jp.gr.java_conf.hangedman.html_template HTMLParser]
   [jp.gr.java_conf.hangedman.html_template HTMLParserListener])

  (:gen-class :name jp.gr.java_conf.hangedman.HtmlParserSimpleListener
              :implements [jp.gr.java_conf.hangedman.html_template.HTMLParserListener]
              :init init
              :state state
              :methods [[output [] String]])

  (:use
   [clojure.tools.logging]
   [hiccup.core]))

(defn setfield
  [this key value]
  (swap! (.state this) into {key value}))

(defn getfield
  [this key]
  (@(.state this) key))

(defn -init []
  [[] (atom {:sexpr-html nil :cur-tag nil})])

(defn -output
  [this]
  ;; return HTML with hiccup
  (clojure.pprint/pprint (getfield this :sexpr-html))
  (html (getfield this :sexpr-html)))

(defn -enterHtmlDocument [this ctx])
(defn -exitHtmlDocument [this ctx])
(defn -enterHtmlElements [this ctx])
(defn -exitHtmlElements [this ctx])
(defn -enterHtmlElement [this ctx])
(defn -exitHtmlElement [this ctx])
(defn -enterHtmlContent [this ctx])
(defn -exitHtmlContent [this ctx])
(defn -enterHtmlAttribute [this ctx])
(defn -exitHtmlAttribute [this ctx])
(defn -enterHtmlAttributeName [this ctx])
(defn -exitHtmlAttributeName [this ctx])
(defn -enterHtmlAttributeValue [this ctx])
(defn -exitHtmlAttributeValue [this ctx])
(defn -enterHtmlTagName [this ctx])
(defn -exitHtmlTagName [this ctx])
(defn -enterHtmlChardata [this ctx])
(defn -exitHtmlChardata [this ctx])
(defn -enterHtmlMisc [this ctx])
(defn -exitHtmlMisc [this ctx])
(defn -enterHtmlComment [this ctx])
(defn -exitHtmlComment [this ctx])
(defn -enterXhtmlCDATA [this ctx])
(defn -exitXhtmlCDATA [this ctx])
(defn -enterDtd [this ctx])
(defn -exitDtd [this ctx])
(defn -enterXml [this ctx])
(defn -exitXml [this ctx])
(defn -enterScriptlet [this ctx])
(defn -exitScriptlet [this ctx])
(defn -enterScript [this ctx])
(defn -exitScript [this ctx])
(defn -enterStyle [this ctx])
(defn -exitStyle [this ctx])

(defn -enterEveryRule [this ctx])
(defn -exitEveryRule [this ctx])
(defn -visitTerminal [this node])
(defn -visitErrorNode [this node])
