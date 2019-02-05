(ns james-cli.core
  (:require [clojure.string :as str]
            [james.core :refer [run-plugins]]
            [james.plugins.file-finder :as ff])
  (:import [jline.console ConsoleReader])
  (:gen-class))

(defn- print-prompt
  "Prints the prompt."
  [input]
  (print (str "\033[2K\r> " input))
  (flush))

(defn- handle-input
  "Handles the special inputs, dispatches the runner.
  Returns nil if we should exit."
  [input keyint]
  (case keyint
    ;; Backspace
    127 (apply str (butlast input))
    ;; c-h (like backspace)
    8 (apply str (butlast input))
    ;; c-w, delete last word
    23 (-> input
           (str/split #"\s+")
           butlast
           (->> (str/join " ")))
    ;; Return
    13 nil
    ;; Default
    (str input #_keyint (char keyint))))

(defn -main []
  (loop [input ""
         results []]
    (print-prompt input)
    (let [cr (ConsoleReader.)
          keyint (.readCharacter cr)
          new-input (handle-input input keyint)
          new-results (run-plugins input [ff/file-finder-plugin])]
      (if (nil? new-input)
        (print "\033[2K\r")
        (recur new-input new-results)))))
