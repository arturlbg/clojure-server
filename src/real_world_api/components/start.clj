(ns real-world-api.components.start
  (:require [com.stuartsierra.component :as component]))

(defrecord ExampleComponent
  [config]
  component/Lifecycle

  (start [component]
    (println ";; Starting ExampleComponent")
    (assoc component :state ::started))

  (stop [component]
    (println ";; Stopping ExampleComponent")
    (assoc component :state nil)))

(defn new-example-coomponent
  [config]
  (map->ExampleComponent {:config config}))
