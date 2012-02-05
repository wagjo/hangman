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

(ns hangman.words
  "Obtaining words for hangman."
  (:require [hangman.util :as util]
            [incanter.core :as incanter]
            [incanter.charts :as charts]
            [clojure.java.io :as jio]))

;;;; STEP 1: Where is the list of words located

;; URL of a .zip file containing list of words
(def words-url
  "http://www.passwordy.net/data/aktul1/En.500nouns.txt.ZIP")

(def words-local
  (jio/resource "hangman/words.zip"))

;;;; STEP 2: Get list of words

(comment

  ;; word list reader
  (util/unzip-from-url words-local)

  ;; whole word list as a single string  
  (slurp (util/unzip-from-url words-local))
  
  ;; count number of characters
  (let [words (slurp (util/unzip-from-url words-local))]
    (count words))
  
  ;; char seq on word list  
  (seq (slurp (util/unzip-from-url words-local)))

  ;; NOTE: file contains one word per line, we can separate
  ;;       words by separating lines
  
  ;; line seq on word list  
  (line-seq (util/unzip-from-url words-local))

  ;; count number of words
  (let [words (line-seq (util/unzip-from-url words-local))]
    (count words))
  
  )

(defn- get-words
  "Returns seq of words."
  [url]
  (line-seq (util/unzip-from-url url)))

;;;; STEP 3: select only words of given length

(comment

  ;; seq of words
  (get-words words-local)

  ;; number of words
  (count (get-words words-local))

  ;; how long are the words?
  (map count (get-words words-local))

  ;; what is the min and max length of words?
  (let [words (get-words words-local)
        lengths (map count words)]
    [(apply min lengths)
     (apply max lengths)])

  ;; how are lengths distributed?
  (let [counts (map count (get-words words-local))]
    (incanter/view (charts/histogram counts :nbins 25)))

  ;; remove words shorter than 5
  (let [words (get-words words-local)
        remove? (fn [word] (< (count word) 5))]
    (remove remove? words))

  ;; only keep words having length between 5 and 7
  (let [words (get-words words-local)
        keep? #(< 4 (count %) 8)]
    (filter keep? words))
  
  )

(defn- trim-words
  "Returns trimmed seq of words.
  Words are trimmed by length.
  Range is specified as a second parameter."
  [words [min max]]
  (let [keep? #(<= min (count %) max)]
    (filter keep? words)))

(comment

  ;; test trim-words
  (let [trimmed-words (trim-words (get-words words-local) [5 7])
        trimmed-count (map count trimmed-words)]
    (-> trimmed-count
        (charts/histogram :nbins 3)
        incanter/view))

  ;; how many words are left?
  (count (trim-words (get-words words-local) [5 7]))

  )

;;;; STEP 4: choose random word

(comment

  ;; get first word
  (first (get-words words-local))

  ;; get second word
  (second (get-words words-local))

  ;; get one hundredth word
  (nth (get-words words-local) 99)

  ;; get five hundredth word
  (nth (get-words words-local) 499)

  ;; get last word
  (last (get-words words-local))

  ;; what if we are out of bounds?
  (nth (get-words words-local) 1337)

  ;; we do not want an exception
  (nth (get-words words-local) 1337 :not-found)

  ;; get random word
  (rand-nth (get-words words-local))

  )

(defn get-random-word
  "Returns random word from the list of words located at url
  (defaults to words-local), having length in a range (defaults
  to [5 7])."
  ([]
     (get-random-word [5 7]))
  ([range]
     (get-random-word range words-local))
  ([range url]
     (-> url
         get-words
         (trim-words range)
         seq
         rand-nth)))

(comment

  ;; test random word
  (get-random-word)

  (get-random-word [0 1])

  (get-random-word [3 3])

  (get-random-word [10 11])

  )
