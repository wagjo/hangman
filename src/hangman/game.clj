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

(ns hangman.game
  "Game mechanics."
  (:require [hangman.words :as words]
            [hangman.solver :as solver]))

;;;; STEP 1: create new game

;; we need to represent game state:
;; - word to guess
;; - seq of guesses

(comment

  ;; example of game state
  {:word "hangman"
   :guesses [\h \a \c]}

  )

(defn create-game
  "Returns new game with word."
  ([]
     (create-game (words/get-random-word)))
  ([word]
     {:word word}))

(comment

  ;; try game creation
  (create-game)

  )

;;;; STEP 2: Check if game is over

;; define maximum number of wrong guesses before the game is over
(def max-wrong-guesses 5)

(defn lost?
  "Returns true if game is lost. Returns false otherwise."
  [game] 
  (let [wrong-guesses (solver/wrong-guesses (:word game)
                                            (:guesses game))]
    (> (count wrong-guesses) max-wrong-guesses)))

(defn won?
  "Returns true if game is won. Return false otherwise."
  [game]
  (solver/won? (:word game) (:guesses game)))

(defn game-over?
  "Return true if game is over. Returns false otherwise."
  [game]
  (or (lost? game)
      (won? game)))

(comment

  ;; is the game lost?
  (lost? {:word "hangman" :guesses [\h \a \c]})
  (lost? {:word "hangman" :guesses [\h \a \c \c \c \f \d \p]})

  ;; is the game won?
  (won? {:word "hangman" :guesses [\h \a \n \g \m]})

  ;; and is it over?
  (game-over? {:word "hangman" :guesses [\h \a \n \g \m]})
  
  )

;;;; STEP 3: Create function for adding next guess

(comment

  ;; example of game state
  {:word "hangman"
   :guesses [\h \a \c]}

  ;; obtain guesses
  (:guesses {:word "hangman", :guesses [\h \a \c]})

  ;; add new guess to the seq of guesses
  (conj [\h \a \c] \g)

  ;; update game state to include new guess
  (let [game {:word "hangman", :guesses [\h \a \c]}]
    (update-in game [:guesses] conj \g))

  )

(defn add-next-guess
  "Returns updated game with guess added. Adds guess only if game
  is not over yet."
  [game guess]
  (if-not (game-over? game)
    (update-in game [:guesses] conj guess)
    game))

;;;; STEP 4: get information for player

(defn get-info
  "Returns information for player about the game."
  [game]
  (let [wrong-guesses (solver/wrong-guesses (:word game)
                                            (:guesses game))]
    {:hint (if (lost? game)
             (:word game)
             (solver/match (:word game)
                           (:guesses game)))
     :wrong-guesses wrong-guesses
     :wrong-guesses-left (max 0
                              (- max-wrong-guesses
                                 (count wrong-guesses)))
     :state (cond
             (won? game) :won
             (lost? game) :lost
             :else :ready)}))

(comment

  ;; test get-info
  (get-info {:word "hangman"})
  (get-info {:word "hangman" :guesses [\h \a \c \n \g]})
  (get-info {:word "hangman" :guesses [\h \a \c \n \g \m]})
  (get-info {:word "hangman" :guesses [\h \a \c \d \e \f \c]})
  (get-info {:word "hangman" :guesses [\h \a \c \d \e \f \c \d]})
  (get-info {:word "hangman" :guesses
             [\h \a \c \d \e \f \c \m \g \n]})

  )
