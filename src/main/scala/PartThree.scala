
object Day10Take2 extends App {

  // @annotation.tailrec def groupRuns[A](c: Seq[A], acc: Seq[Seq[A]] = Seq.empty): Seq[Seq[A]] = c match {
  //   case Seq() => 
  //     acc
  //   case xs => 
  //     val (same, rest) = xs.span { _ == xs.head }
  //     groupRuns(rest, acc :+ same)
  // }

  // val input = "3113322113"

  // def mkRun(xs: String) = 
  //   groupRuns(xs) map { run => run.length.toString ++ run.head.toString } mkString ""

  // @annotation.tailrec def compute(i: Int, xs: String): String = {
  //   if (i == 0) return xs
  //   println(i)
  //   compute(i-1, mkRun(xs))
  // }

  // println (      compute(50, input).length        )



}