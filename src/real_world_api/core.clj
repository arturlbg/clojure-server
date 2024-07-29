(ns real-world-api.core
  (:require [com.stuartsierra.component :as component]
            [real-world-api.config :as config]
            [real-world-api.components.start :as example-component]
            [real-world-api.components.pedestal-component :as pedestal-component]))

(defn real-world-api-system
  [config]
  (component/system-map
    :example-component (example-component/new-example-coomponent
                         config)
    :pedestal-component
    (component/using (pedestal-component/new-pedestal-component
                       config)
                     [:example-component])))

(defn -main
  []
  (let [system (-> (config/read-config)
                   (real-world-api-system)
                   (component/start-system))]
    (println "Starting Real-World API Clojure Service with config")
    (.addShutdownHook
      (Runtime/getRuntime)
      (new Thread #(component/stop-system system)))))



