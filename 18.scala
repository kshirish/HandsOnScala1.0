object BindExample {
   def main(args: Array[String]) {

      val multiplyBy = 6;
      val boundFunc = log(multiplyBy, _ : Int)  // second parameter was left unbound
      
      println(boundFunc(3));	//	18
      println(boundFunc(2));	//	12
      println(boundFunc(4));	//	24
   }

	def log(x: Int, y: Int)  =	{
    	x*y;
	}
}