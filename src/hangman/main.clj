;; Copyright (C) 2012, Jozef Wagner.
;;
;; The use and distribution terms for this software are covered by the
;; Eclipse Public License 1.0
;; (http://opensource.org/licenses/eclipse-1.0.php) which can be found
;; in the file epl-v10.html at the root of this distribution.
;;
;; By using this software in any fashion, you are agreeing to be bound
;; by the terms of this license.
;;
;; You must not remove this notice, or any other, from this software.

(ns hangman.main
  "Entry point for Hangman."
  (:gen-class))

(defn- start-server
  []
  (println "Hangman in server mode."))

(defn- start-desktop
  []
  (println "Hangman in desktop mode."))

(defn -main
  [& args]
  (condp = (first args)
    "server" (start-server)
    "desktop" (start-desktop)
    (println "Welcome to Hangman! Valid arguments are:
server - start hangman in web server mode.
desktop - start hangman as a desktop app.")))
