object CallByName {
  def main(args: Array[String]) {
    delayed(time());
  }

  def time() = {
    println("Getting time in nano seconds");
    System.nanoTime;
  }

  def delayed(t: => Long) = {

    var l = 0;

    for (l <- 0 to 20) {
      // `t` is pointing to the block of code which was passed
      // therefore `t` will get a new value everytime
      println(t);
    }

  }
}
