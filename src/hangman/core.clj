(ns hangman.core
  (require [hangman.util :as util]))

(def words-url "http://www.passwordy.net/data/aktul1/En.500nouns.txt.ZIP")

(comment
  (line-seq (util/unzip-from-url "slova.zip"))
  )
