/**
Scalding with Json tutorial part 0.

To run this job:
  yarn jar target/scalding-tutorial-0.14.0.jar JsonTutorial0 --local --json

Check the output:
  cat target/data/jsonoutput0.tsv

**/

import com.twitter.scalding.{Job, Args, JsonLine, Tsv}

class JsonTutorial0(args: Args) extends Job(args) {
  JsonLine("data/session.json", ('sessionId)).read
    .groupBy('sessionId){_.size}
    .write(Tsv("target/data/jsonoutput0.tsv"))
}
