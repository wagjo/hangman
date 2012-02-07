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
  ;; ensure file is compiled so it can be easily called from java.
  (:gen-class)
  (:require [hangman.desktop :as desktop]))

;; this is how you define main function
(defn -main
  "Main entry point for Hangman."
  [& args]
  (desktop/start-game!))
