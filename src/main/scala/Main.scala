object Day20 extends App {

  def presents(n: Int) = {
    val res = (1 to n) map { i => if (n % i == 0) 10*i else 0 }
    res.sum
  }

  // (1 to 10) map { presents(_) } foreach println
  val res = Stream.from(1000000) takeWhile { presents(_) < 36000000 }  
  // val res = Stream.from(1000000) map { presents(_) }  foreach println

  println (res.last)


}