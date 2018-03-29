(ns jp.gr.java_conf.hangedman.html_template_spec
  (:require
   [clojure.test :refer :all]
   [speclj.core :refer :all])
  (:import
   [jp.gr.java_conf.hangedman HtmlTemplate])
  )

(describe "Call (new HTMLTemplate {:filename '*.tmpl'})"
          (it "will return a instance"
              (should (instance?
                       HtmlTemplate
                       (new HtmlTemplate {:filename 'test.tmpl'})))))

(run-specs)
