(defproject html-template "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [clj-antlr/clj-antlr "0.2.4"]
                 ]
  :main ^:skip-aot html-template.core
  :target-path "target/%s"
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :java-source-paths ["src/main/java"]
  :resource-paths ["src/main/resources"]
  :plugins [[lein-antlr "0.3.0"]]
  :antlr-src-dir "src/main/antlr"
  :antlr-dest-dir "src/main/java"
  :antlr-options { :package "html_template.core"}
  :profiles {:uberjar {:aot :all}}
  )
