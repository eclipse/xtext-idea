# IntelliJ IDEA Support For The Xtext Framework and the Xtend Programming Language

**NOTE: This project is currently not maintained. If you are interested in supporting it, please [get in contact with us on Gitter](http://gitter.im/eclipse/xtext).**

This repository contains all IntelliJ IDEA related code for Xtext, including
 - the general IDEA support for Xtext languages,
 - the Xtext SDK for IDEA itself.
 - the Xtend Programming Language

## How To Build

Checkout and run `./gradlew build`.

Note: Due to dependencies on certain test projects (see [issue #35](https://github.com/eclipse/xtext-idea/issues/35)), the build currently requires to fetch upstream Xtext dependencies from from their respective Maven repositories on the [Jenkins server](http://services.typefox.io/open-source/jenkins/). This behavior can be switched off with `-PuseJenkinsSnapshots=false`.

## Continuous Integration

This project is built by the [xtext-idea multi-branch job on Jenkins](http://services.typefox.io/open-source/jenkins/job/xtext-idea/).
