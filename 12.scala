package level1 {
	package level2 {
		class Person {
			private var someVar = 23;
			var anotherVar = "Hello";

			def sayHi(x: Int): String = {
				println("Hi to " + x);
				return "Bye";
			}
		}
	}
}