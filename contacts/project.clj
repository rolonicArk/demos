(defproject
  boot-project
  "0.0.0-SNAPSHOT"
  :dependencies
  [[adzerk/boot-cljs "1.7.228-1"]
   [adzerk/boot-cljs-repl "0.3.3"]
   [com.cemerick/piggieback "0.2.1"]
   [weasel "0.7.0"]
   [org.clojure/tools.nrepl "0.2.12"]
   [adzerk/boot-reload "0.4.12"]
   [hoplon/boot-hoplon "0.2.2"]
   [hoplon "6.0.0-alpha16"]
   [org.clojure/clojure "1.9.0-alpha10"]
   [org.clojure/clojurescript "1.9.183"]
   [tailrecursion/boot-jetty "0.1.3"]]
  :source-paths
  ["src" "assets"])