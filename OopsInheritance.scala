object OopsInheritance {
	def main(args: Array[String]) {
	   	class Super {
	    	protected def someFunc() { println("OK") }
	   	}

	   	class Sub extends Super {
	    	someFunc();	// OK
	   	}

	   	class Other {
	    	// (new Super()).someFunc() // Error: someFunc is not accessible
	   	}

	   	new Sub();	// OK
	}
}