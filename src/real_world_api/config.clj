(ns real-world-api.config
  (:require [aero.core :as aero]))

(defn read-config
  []
  (aero/read-config "resources/config.edn"))
