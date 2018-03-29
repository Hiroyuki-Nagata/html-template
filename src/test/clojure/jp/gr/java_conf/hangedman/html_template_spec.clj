(ns jp.gr.java_conf.hangedman.html_template_spec
  (:require
   [clojure.test :refer :all]
   [speclj.core :refer :all])
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
          (it "will return String at least"
              (let [template (new HtmlTemplate {:filename 'dummy.tmpl'})]
                (should (= String (type (.param template "")))))))

(run-specs)
