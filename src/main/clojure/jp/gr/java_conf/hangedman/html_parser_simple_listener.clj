(ns jp.gr.java_conf.hangedman.html_parser_simple_listener
  (:import
   [org.antlr.v4.runtime ParserRuleContext]
   [org.antlr.v4.runtime.misc NotNull]
   [org.antlr.v4.runtime.tree ErrorNode]
   [org.antlr.v4.runtime.tree TerminalNode]
   [jp.gr.java_conf.hangedman.html_template HTMLParser]
   [jp.gr.java_conf.hangedman.html_template HTMLParserListener])

  (:gen-class :name jp.gr.java_conf.hangedman.HtmlParserSimpleListener
              :implements [jp.gr.java_conf.hangedman.html_template.HTMLParserListener])

  (:use
   [clojure.tools.logging]))

(defn -enterHtmlDocument [this ctx] (debug "enter HTML doc!"))
(defn -exitHtmlDocument [ctx] (debug "exit HTML doc!"))
(defn -enterHtmlElements [ctx])
(defn -exitHtmlElements [ctx])
(defn -enterHtmlElement [ctx])
(defn -exitHtmlElement [ctx])
(defn -enterHtmlContent [ctx])
(defn -exitHtmlContent [ctx])
(defn -enterHtmlAttribute [ctx])
(defn -exitHtmlAttribute [ctx])
(defn -enterHtmlAttributeName [ctx])
(defn -exitHtmlAttributeName [ctx])
(defn -enterHtmlAttributeValue [ctx])
(defn -exitHtmlAttributeValue [ctx])
(defn -enterHtmlTagName [ctx])
(defn -exitHtmlTagName [ctx])
(defn -enterHtmlChardata [ctx])
(defn -exitHtmlChardata [ctx])
(defn -enterHtmlMisc [ctx])
(defn -exitHtmlMisc [ctx])
(defn -enterHtmlComment [ctx])
(defn -exitHtmlComment [ctx])
(defn -enterXhtmlCDATA [ctx])
(defn -exitXhtmlCDATA [ctx])
(defn -enterDtd [ctx])
(defn -exitDtd [ctx])
(defn -enterXml [ctx])
(defn -exitXml [ctx])
(defn -enterScriptlet [ctx])
(defn -exitScriptlet [ctx])
(defn -enterScript [ctx])
(defn -exitScript [ctx])
(defn -enterStyle [ctx])
(defn -exitStyle [ctx])
(defn -enterEveryRule [ctx])
(defn -exitEveryRule [ctx])
(defn -visitTerminal [node])
(defn -visitErrorNode [node])
