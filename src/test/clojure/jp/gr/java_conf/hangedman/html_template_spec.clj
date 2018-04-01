(ns jp.gr.java_conf.hangedman.html_template_spec
  (:require
   [clojure.test :refer :all]
   [speclj.core :refer :all])
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
                       (new HtmlTemplate {:filename 'test.tmpl'}))))
          (it "will return a instance (with some params)"
              (should (instance?
                       HtmlTemplate
                       (new HtmlTemplate {:filename 'test.tmpl'
                                          :option 'value'})))))

(describe "Call param() return the value set to a param"

          (it "will return empty String if there is no setting"
              (let [template (new HtmlTemplate {:filename 'dummy.tmpl'})
                    ret (.param template "PARAM")]
                (debug ret)
                (should (nil? ret))))

          (it "will return values to be configured"
              (let [template (new HtmlTemplate {:filename 'dummy.tmpl'})
                    set-val (.param template {"PARAM" "value"})
                    get-val (.param template "PARAM")]
                (debug set-val)
                (should (nil? set-val))
                (should (= "value" get-val))))
          )

(run-specs)
