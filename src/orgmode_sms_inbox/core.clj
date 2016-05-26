(ns orgmode-sms-inbox.core
  (:require [compojure.core :refer :all]
            [org.httpkit.server :refer [run-server]]
            [clojure.pprint :refer [pprint]]
            [org.httpkit.client :as client]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.params :refer :all]))

(def ifttt-dropbox-url (System/getenv "IFTTT_DROPBOX_URL"))
(def allowed-incoming-number (System/getenv "ALLOWED_INCOMING_NUMBER"))

(defn return-twilio-message [message]
  {:status 200
   :headers { "Content-Type" "application/xml" }
   :body (str "<?xml version=\"1.0\" encoding=\"UTF-8\" ?> <Response> <Message>" message "</Message> </Response>")})

(def return-unauthorized { :status 401 })

(defn send-to-ifttt [content]
  (client/post ifttt-dropbox-url
               { :form-params { :value1 content } }
               (fn [{:keys [status error body headers]}]
                 (println status)
                 (println error)
                 (println body)
                 (println headers))))

(defn incoming-text [request]
  (let [incoming-number (get-in request [:params "From"])
        incoming-message (get-in request [:params "Body"])]
    (if (= incoming-number allowed-incoming-number)
      (do
        (send-to-ifttt incoming-message)
        (return-twilio-message "Got it, keep on truckin'!"))
      (do
        (binding [*out* *err*]
          (println (str "Bad number! Got " incoming-number ", wanted " allowed-incoming-number)))
        return-unauthorized))))

(defroutes app
  (POST "/incoming" [request] incoming-text))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "5000"))]
    (run-server (wrap-params app :params) {:port port})
    (println (str "listening on port " port))))
