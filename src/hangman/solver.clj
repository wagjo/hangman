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

(ns hangman.solver
  "Match guesses.")

;;;; STEP 1: match word with chosen characters

(comment

  ;; a word
  "hangman"

  ;; word converted to seq of chars
  (seq "hangman")

  ;; go through the word and check if characters are equal
  (map #(= % \a) "hangman")
  
  ;; we can use set to check for equality
  (map #{\a} "hangman")

  ;; we can put more chars into set
  (map #{\a \h \c} "hangman")

  ;; replace nils with underscore character
  (let [matches (map #{\a \h \c} "hangman")]
    (replace {nil \_} matches))

  ;; put it all together
  (let [replace-map #{\a \h \c}
        replace-fn (fn [char] (if (replace-map char)
                               char
                               \_))]
    (map replace-fn "hangman"))

  ;; convert seq of chars back to string
  (str \h \a \n \g \m \a \n)
  
  )

(defn match
  "Returns partially revealed word, based on guesses stored in
  chars seq. Not yet guessed characters are replaced with underscore
  character."
  [word guesses]
  (let [guess-set (set guesses)
        replace-fn #(if (guess-set %) % \_)
        matched-seq (map replace-fn word)]
    (apply str matched-seq)))

;; Clojure allows you to be very terse, but very often,
;; resulting code won't look very pleasant. Omitting docstring
;; is another very common mistake caused by programmer's ignorance
;; and very high self-esteem.
;; Every time you omit a docstring, Rich kills a kitten.
;; Please, think of the kittens!
(defn ugly-match [w g] (apply str (map #(if ((set g) %) % \_) w)))

(comment

  ;; test match fn
  (match "hangman" [\h \n \c])
  (ugly-match "hangman" [\h \n \c])

  ;; test if no guess characters are given
  (match "hangman" nil)

  )

;;;; STEP 2: obtain wrong guesses

(comment

  ;; removes guessed characters from a word
  (remove (set "hangman") #{\h \c \n})

  ;; convert seq of wrong guesses to a set, in order to remove
  ;; duplicate guesses
  (set (remove (set "hangman") [\h \c \a \c]))

  )

(defn wrong-guesses
  "Returns set of wrong guesses for a given word.
  Returns empty set if there are no wrong guesses."
  [word guesses]
  (set (remove (set word) guesses)))

(comment

  ;; test wrong-guesses fn
  (wrong-guesses "hangman" [\h \a \c \c])

  (wrong-guesses nil [\h])

  (wrong-guesses "hangman" nil)
  
  )

;;;; STEP 3: check if game is over

(defn won?
  "Returns true if guesses contains all characters from word.
  Returns nil otherwise."
  [word guesses]
  (let [guess-set (set guesses)]
    (empty? (remove guess-set word))))

(comment

  ;; test won? fn
  (won? "hangman" [\h \a \n \g \c])

  (won? "hangman" [\h \a \n \g \m \c])

  (won? nil [\h])

  (won? "hangman" nil)

  )
