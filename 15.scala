object FlatMaps {
	def main(args: Array[String]) {
		println(List(1,2,4,5,7,8).map(x => someFun(x)));
		println(List(1,2,4,5,7,8).flatMap(x => someFun(x)));	// flattens the list
	}

	def someFun(x: Int) = if(x > 2) Some(x) else None
}