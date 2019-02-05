(defproject james-cli "0.1.1-SNAPSHOT"
  :description "James Launcher - CLI version"
  :url "http://github.com/sulami/james-cli"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [james-server "0.1.1"]
                 [jline "2.14.6"]]
  :main ^:skip-aot james-cli.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
