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

* git
* leiningen
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

Study introductory clojure tutorials:

1. [Introductory presentation](https://docs.google.com/present/edit?id=0ARVWfleXxysoZGdrMjhtN2ZfNDg2N2pqOWRjNw)
2. [src/tutorial/core.clj](https://github.com/wagjo/hangman/blob/master/src/tutorial/core.clj)

Study hangman source files:

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

Following is the list of challenges to improve hangman game. They 
are not ordered by difficulty and they range from easy to very hard. 
These challenges require knowledge of Clojure beyond what is 
explained in this tutorial.

* modify words.clj so it does not read from file every time word is 
requested, but only once.
* modify words.clj so it will never return same word twice, yet it 
will return a random word from the list.
* modify words.clj so that word list is not in external file, but
inside words.clj. Create word list without tedious manual writing.
* make game harder by counting repeated guesses of same wrong letter
 not just once. For example, guessing \a \c \c on "floor" will 
decrement number of guesses left by 3.
* when writing wrong guesses, write "A, B and C", instead of "A, B, C"
* write "one guess left" instead of "1 left"
* write "no guesses left" instead of "0 left"
* track number of games won and number of games played and display
it after each game.
* persists game statistics to disk or database, and continue game
where it was left.
* persists game data into graph database
* create user profiles. When game starts, ask for user name and
load his profile.
* make profiles password protected.
* make password storage safe by using bcrypt.
* make 3 types of difficulty. user can choose between easy, normal 
and hard. Difficulty will determine number of guesses and length of
the words. Place all global game configuration settings in separate
.clj file.
* use different GUI library
* port Hangman to ClojureScript
* port Hangman to Clojure-CLR
* create web app port of Hangman
* make the server part of web app port to run on node.js
* port Hangman to Android
* support multiple concurrent players
* support multiplayer game, so that players guess one word together
* draw a nice hangman, updating his limbs as the game progresses
* implement scoring system, which will score game based on number of
wrong guesses
* support hints. At each game, user can request for one hint, which
will reveal one correct letter. Lower game score when player uses 
a hint.
* implement leaderboard, showing 10 best players

## License

Copyright (C) 2012, Jozef Wagner.

The use and distribution terms for this software are covered by the
Eclipse Public License 1.0 
(http://opensource.org/licenses/eclipse-1.0.php) which can be found
 in the file epl-v10.html at the root of this distribution.

By using this software in any fashion, you are agreeing to be bound
by the terms of this license.

You must not remove this notice, or any other, from this software.
