object DefaultParam {
	def main(args: Array[String]) {
		println(add(3, 6));
		println(add(5));
	}

	def add(a: Int, b: Int = 0) = {
		a + b;
	}
}
