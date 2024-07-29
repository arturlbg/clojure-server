(ns dev
  (:require [com.stuartsierra.component.repl :as component-repl]
            [real-world-api.core :as core]))

(component-repl/set-init
  (fn [_old-system]
    (core/real-world-api-system {:server {:port 3001}})))