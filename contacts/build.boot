(set-env!
  :dependencies '[[adzerk/boot-cljs          "1.7.228-1"]
                  [adzerk/boot-cljs-repl     "0.3.3"]
                  [com.cemerick/piggieback   "0.2.1"]
                  [weasel                    "0.7.0"]
                  [org.clojure/tools.nrepl   "0.2.12"]
                  [adzerk/boot-reload        "0.4.12"]
                  [hoplon/boot-hoplon        "0.2.2"]
                  [hoplon/hoplon             "6.0.0-alpha16"]
                  [org.clojure/clojure       "1.9.0-alpha10"]
                  [org.clojure/clojurescript "1.9.198"]
                  [tailrecursion/boot-jetty  "0.1.3"]]
  :source-paths   #{"src"}
  :resource-paths #{"assets"})

(require
  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-cljs-repl    :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload       :refer [reload]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender]]
  '[tailrecursion.boot-jetty :refer [serve]])

(deftask dev
  "Build project for local development."
  []
  (comp
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs-repl)
    (cljs)
    (serve :port 8000)))

(deftask prod
  "Build project for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (prerender)))
