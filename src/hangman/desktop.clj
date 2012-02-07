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

;; There is no official library or API for desktop GUI.
;; For this tutorial, Seesaw library was chosen.

;; Forward declarations. Sometimes we need to refer to functions
;; which are defined later in source code. Use declare for such cases.
(declare form update-state!)

;; NOTE: By using declare, you can solve forward declarations problem
;;       within one source file. If you have a forward declaration
;;       problem across namespaces, you have a wrong design. Rethink
;;       your namespaces.

;;;; STEP 1: define event handlers

;; This function will be called when player clicks on New Game button
(defn on-new-game
  "Begins a new game.
  Called when new game button is pressed."
  [_]
  (hangman/new-game!)
  (update-state!))

;; This function will be called when player presses a key
(defn on-next-guess
  "Guess next character.
  Called when player presses a key."
  [_]
  (let [guess-widget (ss/select form [:#guess])
        value (cs/lower-case (ss/value guess-widget))]
    ;; using regular expressions to determine valid input
    (when (re-matches #"[a-z]+" (str value))
      ;; only try to guess if it is a letter from a to z
      (hangman/guess! (first value)))
    (ss/value! guess-widget ""))
  (update-state!))

;;;; STEP 2: update game state

(defn get-wrong-guesses
  "Returns string informing about wrong guesses."
  [info]
  (str (if (empty? (:wrong-guesses info))
         "no wrong guesses"
         ;; also transform to uppercase and sort
         (cs/join ", " (->> info
                            :wrong-guesses
                            (map cs/upper-case)
                            sort)))
       ;; also print number of wrong guesses left
       (when-not (= :lost (:state info))
         (str " (" (:wrong-guesses-left info) " left)"))))

(defn update-state!
  "Updates game UI, to reflect a current state."
  []
  ;; find widgets and collect information about current game
  (let [guess-widget (ss/select form [:#guess])
        new-game-widget (ss/select form [:#new-game])
        game-state-widget (ss/select form [:#game-state])
        info (hangman/get-game-info)]
    ;; print word to guess
    (ss/value! (ss/select form [:#word])
               (cs/upper-case (cs/join " " (seq (:hint info)))))
    ;; print wrong guesses
    (ss/value! (ss/select form [:#wrong]) (get-wrong-guesses info))
    ;; update controls and game state message
    (condp = (:state info)
      :won (do
             (ss/value! game-state-widget "You have won!")
             (ss/config! guess-widget :enabled? false)
             ;; low level java interop to change focus
             (.requestFocus (ss/to-widget new-game-widget)))
      :lost (do
              (ss/value! game-state-widget "You have lost!")
              (ss/config! guess-widget :enabled? false)
              (.requestFocus (ss/to-widget new-game-widget)))
      ;; game is still playing
      (do
        (ss/value! game-state-widget "Make your guess.")
        (ss/config! guess-widget :enabled? true)
        (.requestFocus (ss/to-widget guess-widget))))))

;;;; STEP 3: Define form

;; form defines how the UI will look
(def form
  (ss/frame
   :title "Hangman in Clojure"
   :on-close :exit
   :content
   (ss/grid-panel
    :border 15 :vgap 10 :columns 1
    ;; list of widgets
    :items [;; write current state of game here
            (ss/label :id :game-state
                      :text "N/A"
                      :font "ARIAL-16")
            ;; word to guess goes here
            (ss/label :id :word
                      :text "N/A"
                      :font "ARIAL-BOLD-26"
                      :halign :center)
            ;; list of wrong guesses
            (ss/label :id :wrong
                      :text "N/A"
                      :font "ARIAL-18"
                      :halign :center)
            ;; two widgets in one line
            (ss/horizontal-panel
             :items [(ss/label :text "Type next guess:"
                               :font "ARIAL-16")
                     [:fill-h 20]
                     ;; input widget, to capture key events
                     (ss/text :id :guess
                              :listen [:key-released on-next-guess]
                              :font "ARIAL-BOLD-16"
                              :size [30 :by 30])])
            ;; New Game button
            (ss/button :id :new-game
                       :text "New Game"
                       :listen [:action on-new-game]
                       :font "ARIAL-16")
            ;; credits
            (ss/label :text "https://github.com/wagjo/hangman"
                      :font "ARIAL-16"
                      :halign :right)])))

;; main desktop functions

(defn start-game!
  "Starts a desktop game."
  []
  (ss/native!)
  (update-state!)
  (-> form
      ss/pack!
      ss/show!))

(comment

  ;; WARNING: swank will terminate after game window is closed!
  (start-game!)
  
  )
