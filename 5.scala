object VarArgs {
	def main(args: Array[String]) {
		anotherFunc(34, 45, 56);
		anotherFunc(3, 4, 4, 5, 5, 6);
	}

	def anotherFunc(x: Int*) {
		print("Input: ");
		println(x);
		println(x(2));
	}
}