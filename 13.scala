package Playground

import level1.level2._

object Play {
	def main(args: Array[String]) {

		var p = new Person();

		//println(p.someVar);
		println(p.anotherVar);
		println(p.sayHi(100));
	}
}

// Run:
// scalac 12.scala 13.scala
// scala -cp . Playground.Play