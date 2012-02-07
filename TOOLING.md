# Installation

Notes on installation and usage of leiningen and emacs

## Installing Leiningen

This is how I set up leiningen in Ubuntu:

* `wget https://raw.github.com/technomancy/leiningen/stable/bin/lein`
* `sudo mv ./lein /bin/lein`
* `chmod 755 /bin/lein`
* `lein plugin install swank-clojure 1.4.0`

## Preferred IDE

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
* install with key `x`. If error is displayed, repeat package
installation
* `M-x customize-group`, enter `slime-lisp`
    * enter `slime-lisp`
    * change *Slime Net Coding System* to *utf-8-unix*
    * save it for future sessions

Developing with emacs:

* in one console, launch project with `lein swank`
* in other console, start emacs and connect to swank with
`slime-connect`
* compile buffer with **C-c C-k**
* evaluate expression with **C-x C-e**
* see doc for a function with **C-d C-d d**
