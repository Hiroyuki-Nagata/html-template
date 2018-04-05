(ns jp.gr.java_conf.hangedman.html_parser_base_listener
  (:import
   [org.antlr.v4.runtime ParserRuleContext]
   [org.antlr.v4.runtime.misc NotNull]
   [org.antlr.v4.runtime.tree ErrorNode]
   [org.antlr.v4.runtime.tree TerminalNode]
   [jp.gr.java_conf.hangedman.html_template HTMLParser]
   [jp.gr.java_conf.hangedman.html_template HTMLParserListener])

  (:use
   [clojure.tools.logging])

  (:gen-class :name jp.gr.java_conf.hangedman.HtmlParserBaseListener
              :implements HTMLParserListener
              :methods [
                        [enterHtmlDocument [HTMLParser.HtmlDocumentContext] void]
                        [exitHtmlDocument [HTMLParser.HtmlDocumentContext] void]
                        [enterHtmlElements [HTMLParser.HtmlElementsContext] void]
                        [exitHtmlElements [HTMLParser.HtmlElementsContext] void]
                        [enterHtmlElement [HTMLParser.HtmlElementContext] void]
                        [exitHtmlElement [HTMLParser.HtmlElementContext] void]
                        [enterHtmlContent [HTMLParser.HtmlContentContext] void]
                        [exitHtmlContent [HTMLParser.HtmlContentContext] void]
                        [enterHtmlAttribute [HTMLParser.HtmlAttributeContext] void]
                        [exitHtmlAttribute [HTMLParser.HtmlAttributeContext] void]
                        [enterHtmlAttributeName [HTMLParser.HtmlAttributeNameContext] void]
                        [exitHtmlAttributeName [HTMLParser.HtmlAttributeNameContext] void]
                        [enterHtmlAttributeValue [HTMLParser.HtmlAttributeValueContext] void]
                        [exitHtmlAttributeValue [HTMLParser.HtmlAttributeValueContext] void]
                        [enterHtmlTagName [HTMLParser.HtmlTagNameContext] void]
                        [exitHtmlTagName [HTMLParser.HtmlTagNameContext] void]
                        [enterHtmlChardata [HTMLParser.HtmlChardataContext] void]
                        [exitHtmlChardata [HTMLParser.HtmlChardataContext] void]
                        [enterHtmlMisc [HTMLParser.HtmlMiscContext] void]
                        [exitHtmlMisc [HTMLParser.HtmlMiscContext] void]
                        [enterHtmlComment [HTMLParser.HtmlCommentContext] void]
                        [exitHtmlComment [HTMLParser.HtmlCommentContext] void]
                        [enterXhtmlCDATA [HTMLParser.XhtmlCDATAContext] void]
                        [exitXhtmlCDATA [HTMLParser.XhtmlCDATAContext] void]
                        [enterDtd [HTMLParser.DtdContext] void]
                        [exitDtd [HTMLParser.DtdContext] void]
                        [enterXml [HTMLParser.XmlContext] void]
                        [exitXml [HTMLParser.XmlContext] void]
                        [enterScriptlet [HTMLParser.ScriptletContext] void]
                        [exitScriptlet [HTMLParser.ScriptletContext] void]
                        [enterScript [HTMLParser.ScriptContext] void]
                        [exitScript [HTMLParser.ScriptContext] void]
                        [enterStyle [HTMLParser.StyleContext] void]
                        [exitStyle [HTMLParser.StyleContext] void]
                        [enterEveryRule [ParserRuleContext] void]
                        [exitEveryRule [ParserRuleContext] void]
                        [visitTerminal [TerminalNode] void]
                        [visitErrorNode [ErrorNode] void]]))

(defn -enterHtmlDocument [ctx]
  (debug "enter HTML doc!"))
(defn -exitHtmlDocument [ctx]
  (debug "exit HTML doc!"))
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
