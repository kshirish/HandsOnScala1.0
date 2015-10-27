// Example 0. Class Inheritance

class A {
  def f() = println("I'm f from A")
}

val a = new A

a.f

class AA extends A {
  override def f() = println("I'm f from AA")
}

val aa: A = new AA

aa.f

class AAA extends AA {
  override def f() = println("I'm f from AAA")
}


val aaa: A = new AAA

aaa.f

// Example 1. Trait

trait Shape {
	
	def sayIt(): Unit 

	def attrLength(): Int = {
		return 5
	} 
}

class Point(xc: Int, yc:Int) extends Shape {
	var x:Int = xc
	var y:Int = yc

	override def sayIt() = {
		println("There, I have said it.")
	}

	def move(dx: Int, dy:Int) {
		x += dx
		y += dy
	}

	override def toString():String = "(" + x + " -> " + y + ")"
}

object Test extends Shape {
	override def sayIt() = {
		println("I am an object.")
	}
}


var p = new Point(3, 8)

Test.sayIt()

p.move(2,4)
p.sayIt()
println(p.toString())

// Example 2. Linearisation

trait A {
	def sayIt() = {
		println("A")
	}
}

trait X extends A {
	override def sayIt() = {
		println("X")
		super.sayIt()
	}
}

trait Y extends A {
	override def sayIt() = {
		println("Y")
		super.sayIt()
	}
}

var x = new AnyRef with X
x.sayIt	// X

var y = new AnyRef with Y
y.sayIt	// Y

var xy = new AnyRef with X with Y
xy.sayIt	// Y

var yx = new AnyRef with Y with X
yx.sayIt	// X

// Example 3. Mixins

abstract class AbsIterator {
	type T
	def hasNext: Boolean
	def next: T
}

trait RichIterator extends AbsIterator {
	def foreach(f: T => Unit) {
		while (hasNext) f(next)
	}
}

class StringIterator(s: String) extends AbsIterator {
	type T = Char
	private var counter = 0
	def hasNext = counter < s.length()
	def next: T = {
		val ch = s charAt counter
		counter += 1
		ch		 
	}
}

class Iter extends StringIterator("HelloWorld") with RichIterator

var iter = new Iter
iter foreach println


// Example 4. Anonymous Function

def sayIt(f:Int => Int) = {
	println("Answer: " + f(3))
}


sayIt((x:Int) => {
	println("Anonymous function begins")
	x*x
})


// Example 5. Named parameters and default values

def unitWeight(weight:Double, volume:Double) =  weight/volume

unitWeight(400,12)	// order matters
unitWeight(weight = 400, volume = 12)	// order doesn't matter
unitWeight(volume = 12, weight = 400)	// order doesn't matter

def unitWeight(weight:Double, volume:Double = 10) =  weight/volume	// assiging default values
unitWeight(weight = 400)



// Example 6. Anonymous classes

// One way
val o = new { 
	val n = 1
	var s = "Hi"
	def f(x:Int) = x + 1
}

// // Other way: anonymous classes can be created as subclasses of other (named) classes
class Person {
	private var f = 9
	var name = ""
	var age = 0
	var address = ""
}

// the expression below simultaneously defines an anononymous 
// subclass of Person and creates an instance of it
// and adds new fields to the superclass
val jane = new Person("Jane Doe") {
	age = 19

	val street = "1234 Madison Ave"
	val city = "Coralville"
	address = street + ", " + city

	def incrementAge {
		age = age + 1
	}
}

// Alternative to anonymous classes is to use singleton ones
object jane extends Person("Jane Doe") {
	age = 19

	val street = "1234 Madison Ave"
	val city = "Coralville"
	address = street + ", " + city

	def incrementAge {
		age = age + 1
	}
}

// Example 7. Pattern Matching


// Destructuring
val t = (1,2,3)
val (x1, _, x3) = t // "_" is don't care position

val n = 3
val t = n match {
	case 5 => 1
	case 3 => 10
	case _ => 100
}

// Case classes: Expression evaluation
abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

def evaluate(env: Map[String,Double], expr:Expr): Double = expr match {
	case Var(v) => env(v)
	case Number(n) => n
	case UnOp("-", a) => -evaluate(env, a)
	case BinOp("+", l, r) => evaluate(env, l) + evaluate(env, l)
	case BinOp("*", l, r) => evaluate(env, l) * evaluate(env, l)
}

val env = Map("x" -> 2.0, "y" -> 1.0)
val e = UnOp("-", BinOp("*", Var("x"), BinOp("+", Var("y"), Number(3.5))))	// -(x * (y + 3.5))

evaluate(env, e)

// Case classes: Tree

abstract class Tree
case object Empty extends Tree
case class Node(value:Int, leftChild:Tree, rightChild: Tree) extends Tree

val empty = Empty

val t1 = Node(5, empty, Node(7, empty, empty))

val Node(n1, _, _) = t1	// n1: 5
val Node(n2, _, Node(n3, _, _)) = t1	// n2: 5, n3: 7

// Case classes: A List

abstract class MyList
case class NonEmptyList(head:Int, tail: MyList) extends MyList
case object EmptyList extends MyList

val list = NonEmptyList(3, NonEmptyList(4, NonEmptyList(5,EmptyList)))

// Example 8. Tuple

var t = (3, 48, 7, 11)

t._2	// 48
t._3	// 7

t.productIterator.foreach {
	i => println("Value: " + i)
}

// Example 9. String to Int conversion

def toInt(s: String): Int = {
	try {
		s.toInt
	} catch {
		case e: Exception => 0
	}
}

toInt("34") // 34

// Example 10. Option class 

def toInt(s: String): Option[Int] = {
	try {
		Some(s.toInt)
	} catch {
		case e: Exception => None
	}
}

toInt("34").getOrElse(99)	// 34
toInt("3.78").getOrElse(99)	// 99


// Example 11. Immutable Lists

var l = List(1,4,7)
l :::= List(3,2)		// prepends and return a new list
3::list 				// prepends and return a new list
l = l.:::(List(100))	// prepends and return a new list

l.head
l.tail
l.length

List(1,2,4) == List(1,2,4)	// true
List() == NIl				// true

val x1::x2::x3 = l
x1 = 1
x2 = 4
x3 = List(7)

// Example 12. Loop contructs: Generators
val l = List((1, 2), (1, 4), (6, 7), (3, 6))

for (3 <- List(1, 3, 4, 3, 5, 3, 6, 3))  println("what?")
for (3 <- Range(1, 3))  println("what?")
for (3 <- Array(1, 3, 4, 3, 5, 3, 6, 3))  println("what?")
for (3 <- Set(1, 3, 4, 3, 5, 3, 6, 3))  println("what?")
for (3 <- 1 to 6)  println("what?")

for ( (1, j) <- l )  println(j) 			// only matches pairs starting with 1

for (i <- 1 to 2; j <- 1 to 3) println("i = " + i + ", j = " + j)

for {
	i <- 1 to 2
	j <- 1 to 3
} println("i = " + i + ", j = " + j)

// Nested loops with filtering
for {
	i <- 1 to 2 if i%2 == 0
	j <- 1 to 3 if j%2 == 0
} println("i =" + i + ", j = " + j)

val l = List((1,2),(1,4),(6,7),(3,6))

for ( (i, j) <- l if j == 2 * i )
	println((i, j))


def reverseList(x:List[Int]):List[Int] = {
	var l = List[Int]()
	for (h <- x)  l = h::l
	l
}

// Example 13. Higher-order functions

// Functions can be returned
def make_add(n:Int) =  (x:Int) => x + n
val add6 = make_add(6)
add6(8)


def applyTwice(f:(Int) => Int, x:Int) = f(f(x))
applyTwice(add6, 3)
applyTwice((x:Int) => x*x,3)

val add4 = (_:Int) + 4              // Underscore
val add2 = applyTwice(add1, _:Int)  // Underscore

val sub = (x:Int, y:Int) => x - y 
val sub = (_:Int) - (_:Int)    // each occurrence of _ stands for a different input:

def incrementAll( l:List[Int] ): List[Int] =
  l match {
    case Nil => Nil
    case x::t => (x+1)::incrementAll(t)
  }

def incrementAll( f:(Int) => Int, l:List[Int] ): List[Int] =
  l match {
    case Nil => Nil
    case x::t => f(x)::map(f,t)
  }
  
incrementalAll((x:Int) => x+1, List(3, 8, 6))

// mapping
List((1,4),(3,4),(2,6)).map(p => p._1 + p._2)
Array(1,2,3).map(p => p+1)
Set(1,2,3).map(p => p+1)
Vector(1,2,3).map(p => p+1)

def filter( p:(Int) => Boolean, l:List[Int] ): List[Int] =
  l match {
    case Nil => Nil
    case x::t => if (p(x)) x::filter(p,t) else filter(p,t)
  }

val isEven = (_:Int) % 2 == 0
filter(isEven, List(0,1,2,3,4)) // filters out all odd numbers in the list

// filtering
List("a", "c", "d", "b", "s").filter(_ >= "c")
List(4, 1, 2, 3, 2).filter(_ != 2)

// sorting
List(11,2,6,5,6,9,10).sortWith(_ > _) // decreasing order
List(11,2,6,5,6,9,10).sortWith(_ < _) // increasing order
List(11,2,6,5,6,9,10).sortWith((x,y) => x % 2 == 0)

// foreach
List(11,2,6,5,6,9,10).foreach(println(_))
List(11,2,6,5,6,9,10).foreach(x => println("element = " + x))

var sum = 0 
List(11,2,6,5,6,9,10).foreach(x => sum = sum + x)
println(sum)

for (p <- l) println(p)     // same
l.foreach(p => println(p))  // same

for ((i,j) <- l) println(i+j)                 // same
l.foreach(p => {val (i,j) = p; println(i+j)}) // same

// compose
def compose (f:(Int) => Int, g:(Int) => Int) =  (x:Int) => f(g(x))

val add1 = (_:Int) + 1
val square = (x:Int) => x*x

val f = compose(add1,square)
f(3)  // 10

def twice(f:(Int) => Int) = compose(f,f)
val add2 = twice(add1)
add2(3) // 5

// Parametric polymorphism
// Examples of Generics

def len[T] (l:List[T]):Int = 
	l match {
		case Nil    => 0
		case _ :: t => 1 + len(t)
	}

len( List(1, 2, 3) )
len( List("a", "b") )
len( List((1,2), (3,4)) )
len( List(Array(1,2), Array(3,4)) )
 
def concat[T](l:List[T], m:List[T]):List[T] =
	l match {
		case Nil  => m 
		case h::t => h::concat(t,m)
	}
	
def reverse[T](x:List[T]):List[T] = {
	def rev(l:List[T], rl:List[T]):List[T] = 
	l match {
		case h::t => rev(t, h::rl)
		case Nil => rl
	}
	rev(x,Nil)
}      

def filter[T]( p:(T) => Boolean, l:List[T] ): List[T] =
	l match {
		case Nil => Nil
		case x::t => if (p(x)) x::filter(p,t) else filter(p,t)
	}

def map[T]( f:(T) => T, l:List[T] ): List[T] =
	l match {
		case Nil => Nil
		case x::t => f(x)::map(f,t)
	}
	
// Here, map is generalized even further by not requiring f 
// to necessarily return a value of the same type as its input parameter
def map[S,T]( f:(S) => T, l:List[S] ): List[T] =
	l match {
		case Nil => Nil
		case x::t => f(x)::map(f,t)
	} 

map((x:Int) => x.toDouble, List(1,2,3))               // f is a function from Int to Double
map((x:Int) => x+1, List(1,2,3))                      // f is a function from Int to Int
map((x:(Int,Int)) => x._1 + x._2, List((1,2),(3,6)))  // f is a function from pairs (Int,Int) to Int

// A singleton set
def toSet[A](x:A) = Set(x)
toSet(4)

def rep[A](x:A, n:Int):List[A] = 
	n match {
		case 0 => Nil
		case _ => x::rep(x, n-1)
	}

rep(3.4, 3)
rep("Are we there yet?", 3)

def toTriple[A,B,C](x:A, y:B, z:C) = (x,y,z)
toTriple(3,4,5)

def distinct[T](x:T, y:T) =  !(x == y)
def concat[T](x:T, y:T) =  x.toString ++ " " ++ y.toString

concat("a", "b") 
concat(1, 2)     
concat(1, 2.0)     // 1.0 2.0
concat(1, "a")

def concat[S,T](x:S, y:T) =  x.toString ++ " " ++ y.toString
concat(1, 2.0)    // 1 2.0

// Stack implementation
class Stack[T] {
	private var s = List[T]()  // stack is initially empty 
	
	def push(x:T) =  
		s = x::s
		
	def pop = {
		val (h::t) = s
		s = t
		h
	}
	
	def isEmpty = 
		s == Nil
}

// Creates a string Stack
val ss = new Stack[String]

ss push("a")
ss.push("b")
ss.pop

// Sorted Queue implementation
class SortedQueue[T](better:(T,T) => Boolean) {
	private var q = List[T]()
	
	private def insert(x:T, l:List[T]):List[T] =
		l match {
			case Nil => x::Nil
			case h::t => 
				if (better(x,h))
					x::l
				else
					h::insert(x,t)
		}
		
	def enqueue(x:T) = q = insert(x,q)	// returns Unit
	
	def dequeue = {
		val (h::t) = q
		q = t
		h
	}
	
	def isEmpty = q == Nil
}

val p = new SortedQueue[Int](_ > _)

p.enqueue(1)
p.enqueue(10)
p.enqueue(4)
p.dequeue


// Dictionary implementation
abstract class Dictionary[K,V] {

	def put(key: K, value: V):Unit
	def get(key: K):Option[V]
	def remove(key: K):Unit
	def toList():List[(K,V)]

	def getAll(key: K):List[V] = {
		var l = List[V]()
		for ((k,v) <- toList)
			if (k == key) l = v::l
		l
	}

	// removes all values, if any, associated with the input key 
	def removeAll(key: K):Unit = {
		while (get(key) != None)
			remove(key)
	}

	// converts the dictionary into a string using the list returned by toList
	override def toString():String = {
		val d = toList();
		def toStringAux(l:List[(K,V)]):String =
			l match {
				case Nil => ""
				case (k,v)::Nil => k + " -> " + v
				case (k,v)::t => k + " -> " + v + ", " + toStringAux(t)
			}

		"Dictionary(" + toStringAux(d) + ")"
	}
}

// Subclass of Dictionary, storing the key/values in a list
class ListDictionary[K,V] extends Dictionary[K,V] {
	private var d = List[(K,V)]()

	def put(key: K, value: V) = {
		d = (key, value)::d
	}

	def get(key: K):Option[V] = {
		for ((k,v) <- d)
			if (k == key) return Some(v)
		None
	}

	def remove(key: K) = {
		def removeFrom(l:List[(K,V)]):List[(K,V)] =
			l match {
			case Nil => Nil
			case (k,v)::t => if (key == k) t else (k,v)::removeFrom(t)
			}
		d = removeFrom(d)
	}

	override val toList = d
}
