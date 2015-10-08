object OopsClass {
	def main(args: Array[String]) {

		class A() {
			var x = 1;
			val y = 5;
			private var z = 1;
			def f(l: Int) = {l * l};
		}

		class B extends A() {
			var a = "WooFoo";
		}

		var a = new A();
		var b = new B();

		a.x += 111;
		
		println(a.x);		// 112
		println(a.y);		// 5
		// println(a.z); 	// z is a private variable
		println(a.f(4));	// 16

		println(b.a);		// `WooFoo`
		println(b.x);		// 1
		println(b.y);		// 5
		println(b.f(3));	// 9
	}
}