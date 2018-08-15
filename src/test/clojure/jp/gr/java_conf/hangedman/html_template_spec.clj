(ns jp.gr.java_conf.hangedman.html_template_spec
  (:require
   [clojure.test :refer :all]
   [speclj.core :refer :all]
   [clojure.java.io :as io]
   [jp.gr.java_conf.hangedman.html_template :as html_template])
  (:use
   [clojure.tools.logging])
  (:import
   [jp.gr.java_conf.hangedman HtmlTemplate])
  )

;;
;; ctor
;;
(describe "HTML to AST"
          (it "test")

          (def html "<html>
  <head><title>Test Template</title></head>
  <body>
  My Home Directory is <TMPL_VAR NAME=HOME>
  <p>
  My Path is set to <TMPL_VAR NAME=PATH>
  </body>
  </html>")

          (let [antlr-obj (html_template/parse html)
                ctx (:ctx antlr-obj)
                parser (:parser antlr-obj)
                raw-str(.toStringTree ctx parser)
                s-expr (read-string raw-str)
                ]

            (clojure.pprint/pprint s-expr)

            true
            ))

(run-specs)
