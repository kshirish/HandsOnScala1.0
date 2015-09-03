object Loop {
	def main(args: Array[String]) {

		
		var x = 1;
		var list = List(1, 2, 4, 5, 6);
		var anotherList = List(11, 23, 45);
		
		println("While Loop: ");
		while(x <= 5) {
			println(x);
			x+= 1; 
		}		
		
		println("For Loop: ");
		for(x <- list) {
			println(x);
		}

		println("Integers till 5: ");
		for(x <- 1 to 5) {
			println(x);
		}

		println("Even till 5: ");
		for(x <- 1 to 5 if(x % 2 == 0)) {
			println(x);
		}

		println("for-yield: ");
		var newList = for (i <- 1 to 5) 
			yield {i * 2;}

		println(newList);				// Vector(2, 4, 6, 8, 10)
		println(list zip anotherList);	// List((1, 11), (2, 23), (4, 45))

		for((x, y) <- list zip anotherList) println("X: " + x + ", Y: " + y );
		// X: 1, Y: 11
		// X: 2, Y: 23
		// X: 4, Y: 45

		println("Nested for loops: ");
		for(x <- list; y <- anotherList) println("X: " + x + ", Y: " + y );
		// X: 1, Y: 11
		// X: 1, Y: 23
		// X: 1, Y: 45
		// X: 2, Y: 11
		// X: 2, Y: 23
		// X: 2, Y: 45
		// X: 4, Y: 11
		// X: 4, Y: 23
		// X: 4, Y: 45
		// X: 5, Y: 11
		// X: 5, Y: 23
		// X: 5, Y: 45
		// X: 6, Y: 11
		// X: 6, Y: 23
		// X: 6, Y: 45
	}
}