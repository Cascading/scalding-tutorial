# Scalding Tutorial Project 

## Introduction

This is Twitter's [tutorial] [tutorial] for [Scalding] [scalding] adapted to run
on Hadoop as a standalone job - i.e. without requiring `scald.rb` etc.

This was built as a Scala SBT project by the [Concurrent Inc] [concurrent] team,
in order to integrate the scalding tutorial into the [Cascading SDK][sdk].  It
is based on the excellent work done by [Snowplow Analytics][snowplow] for
porting the [`Wordcount example`][wordcount] to SBT. 

The versioning of the project follows the versions of the scalding release on
which it is based.

Please note that this tutorial uses scala 2.10 and not 2.9.

## Building

Assuming you already have SBT installed:

    $ git clone git://github.com/Cascading/scalding-tutorial.git
    $ cd scalding-tutorial
    $ sbt assembly

The 'fat jar' is now available as:

    target/scalding-tutorial-0.8.5.jar

## Project structure

Some modifications have been done to the code, order to properly work in an SBT
based build.

* the code is now in `src/main/scala/tutorial`
* the data files for the different parts lives now in `data`
* the classes in the matrix tutorial have been renamed to match the file names,
  so that the commandline invocation is similar to the original tutorial
* the documentation of the examples have been adapted to match the new structure

## Running the examples

Each part of the tutorial explains, how to run it properly. However the general
way is always

    $ hadoop jar target/scalding-tutorial-0.8.5.jar <TutorialPart> --local <addtional arguments>

## Copyright and license

Copyright 2012-2013 Concurrent Inc, with significant portions copyright 2012 Twitter, Inc. and Snowplow Analytics Inc.

Licensed under the [Apache License, Version 2.0] [license] (the "License");
you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[tutorial]: https://github.com/twitter/scalding/tree/develop/tutorial
[sdk]: http://cascading.org/sdk
[scalding]: https://github.com/twitter/scalding/
[concurrent]: http://concurrentinc.com
[snowplow]: http://snowplowanalytics.com
[wordcount]: http://github.com/snowplow/scalding-example-project 
[license]: http://www.apache.org/licenses/LICENSE-2.0
