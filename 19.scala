object HigherOrderFunction {

   def main(args: Array[String]) {

      println( apply( layout, 10) )
   }

   // accepts function as an argument
   def apply(f:Int => String, v: Int) = f(v)

   // `layout` accepts any type and returns String	
   def layout[A](x: A) = "[" + x.toString() + "]"
   
}