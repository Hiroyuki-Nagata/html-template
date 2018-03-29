(ns jp.gr.java_conf.hangedman.html_template_spec
  (:require [speclj.core :refer :all]
            [html_template :refer :all]))

(describe "Truth"
          (it "is true"
              (should true))
          (it "is not false"
              (should-not false)))

(run-specs)
