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

(ns hangman.desktop
  "Desktop GUI for hangman."
  (:require [hangman.core :as hangman]
            [seesaw.core :as ss]
            [clojure.string :as cs]))

(declare on-new-game on-next-guess)

;; Form

(def form
  (ss/frame
   :title "Hangman in Clojure"
   :content
   (ss/grid-panel
    :border 15 :vgap 10 :columns 1
    :items [(ss/label :id :game-state
                      :text "N/A"
                      :font "ARIAL-16")
            (ss/label :id :word
                      :text "N/A"
                      :font "ARIAL-BOLD-26"
                      :halign :center)
            (ss/label :id :wrong
                      :text "N/A"
                      :font "ARIAL-18"
                      :halign :center)
            (ss/horizontal-panel
             :items [(ss/label :text "Type next guess:"
                               :font "ARIAL-16")
                     [:fill-h 20]
                     (ss/text :id :guess
                              :listen [:key-released on-next-guess]
                              :font "ARIAL-BOLD-16"
                              :size [30 :by 30])])
            (ss/button :id :new-game
                       :text "New Game"
                       :listen [:action on-new-game]
                       :font "ARIAL-16")
            (ss/label :text "https://github.com/wagjo/hangman"
                      :font "ARIAL-16"
                      :halign :right)])))

;; update game state

(defn update-state!
  []
  (let [guess-widget (ss/select form [:#guess])
        word-widget (ss/select form [:#word])
        new-game-widget (ss/select form [:#new-game])
        game-state-widget (ss/select form [:#game-state])
        wrong-widget (ss/select form [:#wrong])
        info (hangman/get-game-info)]
    (ss/config! word-widget :text (cs/upper-case (:hint info)))
    (ss/value! wrong-widget
               (str
                (if (empty? (:wrong-guesses info))
                  "no wrong guesses"
                  (cs/join ", " (->> info
                                     :wrong-guesses
                                     reverse
                                     (map cs/upper-case)
                                     sort)))
                (when-not (= :lost (:state info))
                  (str " (" (:wrong-guesses-left info) " left)"))))
    (condp = (:state info)
      :won (do
             (ss/config! game-state-widget :text "You have won!")
             (ss/config! guess-widget :enabled? false)
             (.requestFocus (ss/to-widget new-game-widget)))
      :lost (do
              (ss/config! game-state-widget :text "You have lost!")
              (ss/config! guess-widget :enabled? false)
              (.requestFocus (ss/to-widget new-game-widget)))
      (do
        (ss/config! game-state-widget :text "Make your guess.")
        (ss/config! guess-widget :enabled? true)
        (.requestFocus (ss/to-widget guess-widget))))))

;; event handlers

(defn on-new-game
  "Begins a new game.
  Called when new game button is pressed."
  [_]
  (hangman/new-game!)
  (update-state!))

(defn on-next-guess
  "Guess next character.
  Called when player presses a key."
  [_]
  (let [guess-widget (ss/select form [:#guess])
        value (cs/lower-case (ss/value guess-widget))]
    (when (re-matches #"[a-z]+" (str value))
      ;; only try to guess if it is a letter from a to z
      (hangman/guess! (first value)))
    (ss/value! guess-widget ""))
  (update-state!))

;; main desktop functions

(defn start-game!
  "Starts a desktop game."
  []
  (ss/native!)
  (update-state!)
  (-> form
      ss/pack!
      ss/show!))

(start-game!)
