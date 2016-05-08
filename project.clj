(defproject orgmode-sms-inbox "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :user {:plugins [[cider/cider-nrepl "0.12.0-SNAPSHOT"]
                   [refactor-nrepl "0.3.0-SNAPSHOT"]]
         :dependencies [[alembic "0.3.2"]
                        [org.clojure/tools.nrepl "0.2.7"]]}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [http-kit "2.1.18"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]]

  :jvm-opts ["-Xmx1G"]

  :main orgmode-sms-inbox.core)
