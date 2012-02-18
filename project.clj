(defproject hangman "1.0.0"
  :description "Beginners tutorial to Clojure"
  :url "https://github.com/wagjo/hangman"
  :license {:name "Eclipse Public License"}
  ;; every library used in hangman must be declared here, so
  ;; that leiningen will find it
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter "1.3.0-20120120.004852-2"]
                 [seesaw "1.3.0"]]
  :checksum-deps false
  :jvm-opts ["-Dswank.encoding=utf-8"]
  ;; name of main class
  :main hangman.main)
