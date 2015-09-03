object Options {
	def main(args: Array[String]) {
		
		toInt("358") match {
		    case Some(i) => println(i);
		    case None => println("That didn't work.");
		};
	}

	def toInt(str: String): Option[Int] = {

		try {
	        Some(Integer.parseInt(str))
	    } catch {
	        case e: NumberFormatException => None
	    }
	}
}