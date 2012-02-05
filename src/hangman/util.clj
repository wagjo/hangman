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

(ns hangman.util
  "Miscellaneous functions."
  (:import java.io.ByteArrayOutputStream
           java.util.zip.ZipInputStream)
  (:require [clojure.java.io :as jio]))

(defn unzip-from-url
  "Loads zip file from an url, unzips first file
  and returns a reader object on it."
  [url]
  (with-open [a (ZipInputStream. (jio/input-stream url))]
    (let [size (.getSize (.getNextEntry a)) ; this also sets position
          buf (byte-array size)
          os (ByteArrayOutputStream.)]
      (loop [size size]
        (let [read (.read a buf 0 size)]
          (.write os buf 0 read)
          (when (< read size)
            (recur (- size read)))))
      (jio/reader (.toByteArray os)))))
