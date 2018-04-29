(ns jp.gr.java_conf.hangedman.html_template_spec
  (:require
   [clojure.test :refer :all]
   [speclj.core :refer :all]
   [clojure.java.io :as io])
  (:use
   [clojure.tools.logging])
  (:import
   [jp.gr.java_conf.hangedman HtmlTemplate])
  )

;;
;; ctor
;;
(describe "Call new() to create a new Template object"
          (it "will return a instance (with a single template file)"
              (should (instance?
                       HtmlTemplate
                       (new HtmlTemplate {:filename "test.tmpl"}))))
          (it "will return a instance (with some params)"
              (should (instance?
                       HtmlTemplate
                       (new HtmlTemplate {:filename "test.tmpl"
                                          :option 'value'})))))

(describe "Call param() return the value set to a param"

          (it "will return empty String if there is no setting"
              (let [template (new HtmlTemplate {:filename "test.tmpl"})
                    ret (.param template "PARAM")]
                (debug ret)
                (should (nil? ret))))

          (it "will return values to be configured"
              (let [template (new HtmlTemplate {:filename "test.tmpl"})
                    set-val (.param template {"PARAM" "value"})
                    get-val (.param template "PARAM")]
                (debug set-val)
                (should (nil? set-val))
                (should (= "value" get-val))))
          )

(describe "Given a normal HTML file with a few extra tags, the simplest being <TMPL_VAR>"

          (it "will return the HTML source as is if it's not configured any params"
              (let [template (new HtmlTemplate {:filename "repeat.tmpl"})
                    ans (.output template)]
                (debug ans)
                (should-not (nil? ans))))

          (it "can process the HTML source containing attribute name & value"
              (let [template (new HtmlTemplate {:filename "test.tmpl"})
                    ;; fill in some parameters
                    dummy (.param template {:HOME "/home/some/directory"})
                    dummy (.param template {:PATH "/bin;/usr/bin"})
                    ans (.output template)]
                (debug ans)
                (should-not (nil? ans))))

          )

(run-specs)
