(ns app.random
  (:require [app.handler :as handler]))

(def app handler/routes)

(defonce my-contacts
  (atom #{{:first "Ben" :last "Bitdiddle" :email "benb@mit.edu"}
          {:first "Alyssa" :middle-initial "P" :last "Hacker" :email "aphacker@mit.edu"}
          {:first "Eva" :middle "Lu" :last "Ator" :email "eval@mit.edu"}
          {:first "Louis" :last "Reasoner" :email "prolog@mit.edu"}
          {:first "Cy" :middle-initial "D" :last "Effect" :email "bugs@mit.edu"}
          {:first "Lem" :middle-initial "E" :last "Tweakit" :email "morebugs@mit.edu"}}))

(defmethod handler/event-msg-handler :contacts/get-contacts
  [{:as ev-msg :keys [event id ?data ring-req ?reply-fn send-fn]}]
  (handler/chsk-send! (:client-id ev-msg) [(:resp-id ?data) @my-contacts]))

(defn broadcast [resp-id data]
  (let [uids (:any @handler/connected-uids)]
    (doseq [uid uids]
      (handler/chsk-send! uid [resp-id data]))))

(defmethod handler/event-msg-handler :contacts/delete
  [{:as ev-msg :keys [event id ?data ring-req ?reply-fn send-fn]}]
  (let [contact (:contact ?data)]
    (swap! my-contacts disj contact)
    (broadcast :contacts/deleted contact)))
