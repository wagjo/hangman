;; Welcome to introductory Clojure tutorial. This line is a comment.

;; Comments starts with ; and spans to the end of current line

;; Usually, copyright and licence info is placed at the begining of
;; each source file. Like this:


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


;; Clojure programs usually consist of several clojure source files.
;; You are now in a file called core.clj, which is placed
;; in src/tutorial directory.

;; Each file usually represents one namespace. Namespaces group
;; similar functions together and ensure that names from one namespace
;; does not clash with names defined in other one.

;; Clojure file should start with namespace definition. Namespace name
;; must reflect file name and its path in the filesystem.
(ns tutorial.core
  ;; following form makes other namespaces accessible, so we can
  ;; call functions defined in them.
  (:require [clojure.string :as cstr]
            [clojure.set :as cset]))

;; NOTE: Always have at least two segments in namespace name. Do not
;;       create e.g. (ns tutorial ) namespace. It will cause
;;       problems with AOT compilation.

;; NOTE: By convention, most important file in current namespace
;;       segment should be called core.clj. Similarly, if you don't
;;       know how you should call your file, make it core.clj :)

;; symbols are defined like this
(def greeting "Hello") ;; greeting will point to value "Hello"

(def foo (+ 5 5)) ;; foo will point to value 10

;; functions are defined like this
(defn hello
  "Returns hello string."
  [who]
  (str greeting " " who "!"))

;; NOTE: By convention, symbol names are hyphenated. This means that
;;       send-more-money is preferred to sendMoreMoney.

;; If you want to have testing or debugging expressions in code,
;; you can put it inside (comment ) expression.
(comment

  ;; test hello function. In emacs, point cursor at the end of
  ;; following line and press C-x C-e. Do not forget to compile
  ;; file with C-c C-k before evaluating following expression
  (hello "world")

  ;; Note that text inside (comment ) must be a valid clojure code

  ;; print text to REPL console
  (println (hello "world again"))

  ;; let defines local symbols
  (let [people ["mathew" "mark" "luke" "john"]]
    ;; call functions from other namespaces with prefix specified in
    ;; (:require) expression
    (cstr/join ", " people))

  ;; you can refer to previous defined local symbols in let
  (let [people ["mathew" "mark" "luke" "john"]
        message (cstr/join ", " people)]
    (hello message))

  )

;; define vector of evangelists
(def evangelists ["mathew" "mark" "luke" "john"])

;; function which uppercases first letter
(defn uppercase-first
  "Returns word with first letter in upper case."
  [word]
  (if-not (or (empty? word) (nil? word))
    (cstr/join (cons (cstr/upper-case (first word))
                     (rest word)))
    word))

(comment

  ;; test uppercase-first
  (uppercase-first "thomas")
  (uppercase-first nil)
  (uppercase-first "")
  
  )

(defn hello-evangelists*
  "Returns greetings to four evangelists."
  []
  (hello (cstr/join ", " (map uppercase-first evangelists))))

;; previous function can be written also as
(defn hello-evangelists
  "Returns greetings to four evangelists."
  []
  (->> evangelists
       (map uppercase-first)
       (cstr/join ", ")
       hello))

(comment

  (hello-evangelists)

  )

;; three ways to determine wheter person is evangelist

(defn evangelist?
  [name]
  ;; example of simple anonymous function
  (some #(= name %) evangelists))

(defn evangelist?*
  [name]
  (contains? (set evangelists) name))

;; NOTE: use C-c C-d d in emacs to read documentation for selected
;;       function. Point cursor at the text "contains?" and try it.

(defn evangelist?**
  [name]
  ((set evangelists) name))

(comment

  ;; compare return values of following functions

  (evangelist? "paul") ; nil
  (evangelist? "mark") ; true

  (evangelist?* "paul") ; false
  (evangelist?* "mark") ; true

  (evangelist?** "paul") ; nil
  (evangelist?** "mark") ; "mark"

  )

;; NOTE: Limit line length to 70 characters.
;;       File will be more readable.
