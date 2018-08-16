(ns jp.gr.java_conf.hangedman.html_template_spec
  (:require
   [clojure.test :refer :all]
   [speclj.core :refer :all]
   [clojure.java.io :as io]
   [jp.gr.java_conf.hangedman.html_template :as html_template])
  (:use
   [clojure.tools.logging]
   [hickory.core]
   [hickory.render])
  (:import
   [jp.gr.java_conf.hangedman HtmlTemplate])
  )

(defn replace-several [content & replacements]
  (let [replacement-list (partition 2 replacements)]
    (reduce #(apply clojure.string/replace %1 %2) content replacement-list)))

;;
;; ctor
;;
(describe "HTML to hickory parsed tree"
          (it "can convert with specified arguments for replacement"

              (def in-html "<html>
  <head><title>Test Template</title></head>
  <body>
  My Home Directory is <TMPL_VAR NAME=HOME>
  <p>
  My Path is set to <TMPL_VAR NAME=PATH>
  </body>
  </html>")

              (def expect-html "<html>
  <head><title>Test Template</title></head>
  <body>
  My Home Directory is /home/some/directory
  <p>
  My Path is set to /bin;/usr/bin
  </body>
  </html>")

              (let [parsed-doc (parse in-html)
                    hickory-doc (as-hickory parsed-doc)
                    hickory-html (hickory-to-html hickory-doc)]

                (clojure.pprint/pprint in-html)
                (clojure.pprint/pprint hickory-html)

                (= expect-html hickory-html)
                )))

(run-specs)
