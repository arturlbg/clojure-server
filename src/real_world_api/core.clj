(ns real-world-api.core
  (:require [com.stuartsierra.component :as component]
            [real-world-api.config :as config]
            [real-world-api.components.start :as example-component]
            [real-world-api.components.pedestal-component :as pedestal-component]
            [real-world-api.components.in-memory-state-component :as in-memory-state-component]))

(defn real-world-api-system
  [config]
  (component/system-map
    :example-component (example-component/new-example-coomponent
                         config)
    :in-memory-state-component (in-memory-state-component/new-in-memory-state-component
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



