# Hangman

Complete beginner's tutorial to Clojure.

This tutorial is for complete newbies to Clojure. It guides you
through the process of creating a playable and production ready
hangman game.

<img
src="https://github.com/downloads/wagjo/hangman/hangman.png"
 alt="Hangman GUI" title="Hangman GUI" align="left" style="clear:none;" />

## Prerequisites

This tutorial does not cover following topics:

* working with git and github
* installing clojure
* working with leiningen
* anything IDE related

Prerequisites for this tutorial include:

* installed git
* IDE which supports Clojure REPL and can evaluate selected 
expressions from source file in REPL

### Installing Leiningen

This is how I set up leiningen in Ubuntu:

* `wget https://raw.github.com/technomancy/leiningen/stable/bin/lein`
* `sudo cp ./lein /bin/lein`
* `chmod 755 /bin/lein`
* `lein plugin install swank-clojure 1.4.0`

### Preferred IDE

I use Emacs in Ubuntu. This is how I set it up:

* `sudo apt-get install emacs23`
* `git clone git://github.com/technomancy/emacs-starter-kit.git`
* `git checkout remotes/origin/master`
* `mv ./emacs-starter-kit ./.emacs.d`
* launch emacs. In emacs, do this:
* `M-x package-list-packages`
* select following packages with `i` key:
    * clojure-mode
    * clojurescript-mode
    * slime
    * slime-repl
    * highlight-parentheses
* install with key `x`. If error is produced, repeat package
installation
* `M-x customize-group`, enter `slime-lisp`
    * enter `slime-lisp`
    * change Slime Net Coding System to utf-8-unix
    * save it for future sessions

Developing with emacs:

* in one console, launch project with lein swank
* in other console, start emacs and connect to swank with
`slime-connect`
* compile buffer with C-c C-k
* evaluate expression with C-x C-e
* see doc for a function with C-d C-d d

## How to study this tutorial

* clone this repository with `git clone
  git://github.com/wagjo/hangman.git`
* Launch your favorite IDE, and open hangman source files in order
  given below
* Study files from top to bottom, line by line
    * evaluate files and expressions in comments
    * try expressions in repl, experiment
* have [ClojureDocs](http://clojuredocs.org) open

## Lets begin

Study files in following order:

1. Carefully read [this presentation](https://docs.google.com/present/edit?id=0ARVWfleXxysoZGdrMjhtN2ZfNDg2N2pqOWRjNw)
2. [src/tutorial/core.clj](https://github.com/wagjo/hangman/blob/master/src/tutorial/core.clj)
3. [src/hangman/words.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/words.clj)
4. [src/hangman/solver.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/solver.clj)
5. [src/hangman/game.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/game.clj)
6. [src/hangman/core.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/core.clj)
7. [src/hangman/desktop.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/desktop.clj)
8. [src/hangman/main.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/main.clj)
9. [project.clj](https://github.com/wagjo/hangman/blob/master/README.md)

## How to run the game

Type `lein run` to run the game.

### Package game

How to package the game for publishing

* Type `lein uberjar`
* Run packaged game with `java -jar ./hangman-1.0.0-standalone.jar`
    * or doubleclick on hangman-1.0.0-standalone.jar :)

## Challenges

Following challenges require knowledge in clojure beyond what is
explained in this tutorial

* modify words.clj so it does not read from file every time word is 
requested
* modify words.clj so it will never return same word twice, yet it 
will return a random word from the list

## License

Copyright (C) 2012, Jozef Wagner.

The use and distribution terms for this software are covered by the
Eclipse Public License 1.0 
(http://opensource.org/licenses/eclipse-1.0.php) which can be found
 in the file epl-v10.html at the root of this distribution.

By using this software in any fashion, you are agreeing to be bound
by the terms of this license.

You must not remove this notice, or any other, from this software.
