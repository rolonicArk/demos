(ns app.rpc
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
   [javelin.core]))

(defc state {:random nil})
(defc error nil)
(defc loading [])

(defc= random-number (get state :random))

(defn get-state []
  0)

(defn init []
      (get-state)
      (js/setInterval get-state 1000)
      )
