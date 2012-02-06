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

(ns hangman.core
  "Core Hangman functions."
  (:require [hangman.game :as game]))

;;;; STEP 1: game state

;; game state is stored in this var
(def current-game (atom (game/create-game)))

;;; These are state modifying functions

(defn get-game-info
  "Returns current game info."
  []
  (game/get-info @current-game))

(defn new-game!
  "Starts a new game. Returns info for player"
  ([]
     (reset! current-game (game/create-game))
     (get-game-info))
  ([word]
     (reset! current-game (game/create-game word))
     (get-game-info)))

(defn guess!
  "Adds new guess to the game, but only if game is not over.
  Returns info for player."
  [guess]
  (swap! current-game game/add-next-guess guess)
  (get-game-info))

;;; Test the game

(comment

  ;; what is current game info
  (get-game-info)

  ;; what is in the current game var?
  current-game

  ;; what is the state of current game?
  @current-game

  ;; Create new game
  (new-game! "hangman")

  ;; has current game changed
  @current-game

  ;; try some guesses
  (guess! \c)
  (guess! \f)
  (guess! \h)
  (guess! \a)
  (guess! \n)
  (guess! \g)
  (guess! \m)
  
  )
