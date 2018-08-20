(defproject clojure-sandbox "0.0.1-SNAPSHOT"
  :description "Clojure Sandbox"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.namespace "0.2.11"]]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :aot [clojure-sandbox.core]
  :main clojure-sandbox.core
  :test-paths ["test"])
