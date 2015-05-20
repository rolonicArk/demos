(load-file "../build.util.clj")
(require '[build.util :as build])

(set-env!
  :dependencies (conj (build/deps)
                  '[tailrecursion/castra "2.2.2"]
                  '[ring/ring-defaults "0.1.4"]
                  '[compojure "1.3.3"])
  :resource-paths #{"src/static"}
  :source-paths   #{"src/hoplon" "src/castra" "src/cljs"})

(require
  '[adzerk.boot-cljs          :refer [cljs]]
  '[adzerk.boot-reload        :refer [reload]]
  '[tailrecursion.boot-hoplon    :refer [hoplon prerender]]
  '[pandeiro.boot-http           :refer [serve]])

(deftask dev
  "Build project for local development."
  []
  (comp
    (serve :handler 'demo.core/handler :reload true)
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs)))

(deftask prod
  "Build project for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (prerender)))
