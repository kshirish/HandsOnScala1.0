object Functions {
	def main(args: Array[String]) {

		// A Function Definition:
		// def functionName ([list of parameters]) : [return type] = {
		//    function body
		//    return [expr]
		// }

		// function 1
		def f(x: Int) = {x * x}
		println("Answer: " + f(24));

		// function 2
		def g(x: Int) = println(x);
		g(23);

		// calling function 3
		anotherFunc(34);

		// function 4: that returns
		def isEven( a:Int ) : Boolean = {
			return a % 2 == 0; 
		}

		// function 5: Procedures - who do not return 
		def NothingFunc(x: String) : Unit  = {
			println("I am a nothing function.");
		}

		var range = (1 to 5);
		println(range(1));
		println(range.map((x: Int) => x*x));
		println(range.map(2*));
		println(range.map(_*2));

		// returns the last statement
		var newRange = range.map((x:Int) => {
			println("Wee");
			println("Woo");
			x*2;
			x+2;
		});

		println(newRange);

		var filteredRange = range.filter(_%2 == 0);
		println(filteredRange);

		// chaining
		println("Before chaining: " + range);
		print("After chaining: ");
		println(range.map((x: Int) => x*x)
		 filter(_%2 == 0));

		var nums = List(2,4,6,8,9,1,4);
		println(nums.map(x => x + 1).map(x => x * x).map(x => x - 1))
	}

	// function 3
	def anotherFunc(x: Int) {
		println("Input: " + x);
	}
}