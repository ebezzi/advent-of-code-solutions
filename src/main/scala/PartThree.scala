
object Day10Take2 {

  // @annotation.tailrec def groupRuns[A](c: Seq[A], acc: Seq[Seq[A]] = Seq.empty): Seq[Seq[A]] = c match {
  //   case Seq() => 
  //     acc
  //   case xs => 
  //     val (same, rest) = xs.span { _ == xs.head }
  //     groupRuns(rest, acc :+ same)
  // }

  // def next(xs: String) = {

  //   def recurse(i: Int, streak: Int, current: Char, acc: Seq[String] = Seq.empty) : Seq[String] = {

  //     if (i == xs.length) return acc :+ (streak.toString :+ current)

  //     if (xs(i) != current)
  //       recurse(i+1, 1, xs(i), acc :+ (streak.toString :+ current))
  //     else
  //       recurse(i+1, streak+1, current, acc)
  //   }

  //   recurse(0, 0, xs.head).mkString

  // }

  def next(xs: String) = {

    val sb = new StringBuilder

    def recurse(i: Int, streak: Int, current: Char) : Unit = {

      if (i == xs.length) {
        sb.append(streak.toString)
        sb.append(current)
        return
      }

      if (xs(i) != current){
        sb.append(streak.toString)
        sb.append(current)
        recurse(i+1, 1, xs(i))
      } else {
        recurse(i+1, streak+1, current)
      }
    }

    recurse(0, 0, xs.head)
    sb.toString

  }

  val input = "3113322113"

  // println (   next(input)    )



  // def mkRun(xs: String) = 
  //   groupRuns(xs) map { run => run.length.toString ++ run.head.toString } mkString ""

  @annotation.tailrec def compute(i: Int, xs: String): String = {
    if (i == 0) return xs
    println(i)
    compute(i-1, next(xs))
  }

  println (      compute(50, input).length        )





}