object Structs {
	def main(args: Array[String]) {
		var (x, y, z) = ("now", 45, "see");
		// var (x, y, z) = ("now", 45, "see", "me");
		println(x);
		println(y);
		println(z);

		var list = List(1, 23, 45);
		//list(1)  = 33;	// List is immutable
		println(list(1));	// element at index 1
		println(1::List(3,4));
		
		println(());		// empty Range
		println(List());	// empty List

		// too much sugar
		println(1 to 5);
		println(11 until 18);
		println(1 until 15 by 3);
		println(1 to 14 by 3);
	}
}