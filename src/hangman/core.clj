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
  "Core Hangman functions.")

;;;; STEP 1: initialize profiles

(comment

  ;; each profile contains:
  ;; - current game
  ;; - number of games won
  ;; - number of games played
  ;; each game contains:
  ;; - word to guess
  ;; - guesses seq
  ;; it is good to represent a profile as a map
  {:won 3
   :all 5
   :game {:word "antelope"
          :guesses [\f \l \c \d]}}

  ;; we want to support multiple users, each having
  ;; its own profile
  {"Peter" {:won 3
            :all 3} ; does not have active game
   "Mark" {:won 2
           :all 3
           :game {:word "caribou"}} ; game started but not yet guessed
   "Rebecca" {:won 0
              :all 3
              :game {:word "sausage"
                     :guesses [\t \i \n]}}} ; guessing right now
  
  )

;; profiles are maps indexed by user names
(def profiles (atom {}))

(comment

  ;; obtain actual value
  @state
  
  )

;;;; STEP 2: get users profile

(defn get-profile
  "Returns profile for a given user name. Can be nil."
  [user-name]
  (@profiles user-name))

;;;; start new game

;; old game in progress, add to all games
;; if won, add to won games

;;;; new guess

;; add next guess to the current game

