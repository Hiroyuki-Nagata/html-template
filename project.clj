(defproject html-template "0.1.0-SNAPSHOT"
  :description "HTMLTemplate library like Perl's one"
  :url "https://github.com/Hiroyuki-Nagata/html-template"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.2.6"]
                 [org.slf4j/slf4j-api "1.7.0"]
                 [ch.qos.logback/logback-classic "1.0.13"]
                 [clj-antlr/clj-antlr "0.2.4"]
                 [prismatic/plumbing "0.5.5"]
                 [speclj/speclj "3.3.2"]
                 [hiccup/hiccup "1.0.5"]
                 [instaparse "1.4.8"]]
  ;;:main ^:skip-aot jp.gr.java_conf.hangedman.html_template
  :aot [jp.gr.java_conf.hangedman.html_template]
  :target-path "target/%s"
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :java-source-paths ["src/main/java"]
  :resource-paths ["src/main/resources"
                   "src/test/resources"]
  :plugins [[lein-antlr "0.3.0"]
            [speclj "3.3.0"]]
  :antlr-src-dir "src/main/antlr"
  :antlr-dest-dir "src/main/java/jp/gr/java_conf/hangedman/html_template"
  :antlr-options { :package "jp.gr.java_conf.hangedman.html_template"}
  :profiles {:uberjar {:aot :all}}
  )
