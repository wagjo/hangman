<img
src="https://github.com/downloads/wagjo/hangman/hangman-gui.png"
 alt="Hangman GUI" title="Hangman GUI" align="right"/>
# Hangman

Complete beginner's tutorial to Clojure.

This tutorial is for **Clojure newbies**. It guides you
through the process of creating a playable and production ready
hangman game.

## Prerequisites

Prerequisites for this tutorial include:

* Installed git
* IDE which supports Clojure REPL and can evaluate selected 
expressions from source file in REPL

See [notes on
tooling](https://github.com/wagjo/hangman/blob/master/TOOLING.md) 
for some info about leiningen and emacs.

## How to study this tutorial

* clone this repository with `git clone
  git://github.com/wagjo/hangman.git`
* Launch your favorite IDE and open hangman source files in order
  given below
* Study files from top to bottom, line by line
    * evaluate files and expressions in comments
    * **think**, experiment, read official docs and [ClojureDocs](http://clojuredocs.org)

## Lets begin

Study following resources dealing with basic Clojure stuff:

1. [Introductory presentation](https://docs.google.com/present/edit?id=0ARVWfleXxysoZGdrMjhtN2ZfNDg2N2pqOWRjNw)
2. [src/tutorial/core.clj](https://github.com/wagjo/hangman/blob/master/src/tutorial/core.clj)

Study following hangman source files:

1. [src/hangman/words.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/words.clj)
2. [src/hangman/solver.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/solver.clj)
3. [src/hangman/game.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/game.clj)
4. [src/hangman/core.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/core.clj)
5. [src/hangman/desktop.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/desktop.clj)
6. [src/hangman/main.clj](https://github.com/wagjo/hangman/blob/master/src/hangman/main.clj)
7. [project.clj](https://github.com/wagjo/hangman/blob/master/project.clj)

## How to run the game

Type `lein run` to run the game.

### Package game

* Type `lein uberjar`
* Run packaged game with `java -jar ./hangman-1.0.0-standalone.jar`
    * or doubleclick on hangman-1.0.0-standalone.jar :)

## Challenges

Following challenges require knowledge of Clojure beyond what is
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
