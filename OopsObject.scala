object OopsObject {
	def main(args: Array[String]) {
		object StepBrotherOfSingleton {
			
			var publicVar = 100;
			val publicConstantVar = "Home";
			private var privateVar = "*****";

			private def privateFunc() {
				println("You can't see me.");
			}

			def display(l: Int) {
				privateFunc();
				println("Wee");
			}
		}

		StepBrotherOfSingleton.publicVar = 77;
		// StepBrotherOfSingleton.publicVar = "Wave";		
		// StepBrotherOfSingleton.publicConstantVar = 22;
		

		println(StepBrotherOfSingleton.publicVar);			// 77 
		println(StepBrotherOfSingleton.publicConstantVar);	// `Home`
		// println(StepBrotherOfSingleton.privateVar);
		println(StepBrotherOfSingleton.display(34));		// ()

		object MathLib {
		   	def add( a:Int, b:Int ) : Int = {
		    	var sum:Int = 0
		    	sum = a + b

		    	return sum
		   	}

		   	def isEven( a:Int ) : Boolean = {
				return a % 2 == 0; 
			}

		}

		println(MathLib.add(4,8));
		println(MathLib.isEven(4));		// true	
		println(MathLib.isEven(7));		// false
	}
}