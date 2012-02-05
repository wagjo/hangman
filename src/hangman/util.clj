(ns hangman.util
  (:import java.io.FileInputStream
           java.io.BufferedInputStream
           java.io.ByteArrayOutputStream
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
