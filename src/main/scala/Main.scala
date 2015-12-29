
object Day25 {


  // Enter the code at row 2981, column 3075.

  // element (i,j) is the jth element of diagonal (i+j-1)
  // diagonal n is n(n-1)/2+1
  // formula is (i+j-1)(i+j-2)/2+1+j

  def f(i: Int, j: Int) = (i+j-1)*(i+j-2)/2 + j

  val iter = f(2981, 3075)

  // val code: Stream[Long] = 20151125 #:: code.map(252533 * _ % 33554393)
  // println (      code take iter last      )

  def comp(i: Int, part: Long) : Long = {
    if (i == 0) return part
    comp(i-1, 252533 * part % 33554393)
  }

  println (comp(iter-1, 20151125))


}



object Day24 {

  val input: Seq[Long] = Seq(1, 3, 5, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113)

  val sum = input.sum/4

  val d = (0 to input.length-1).combinations(5) collect Function.unlift({ i => 
    val firstGroup = i map { input.reverse(_) }
    if (firstGroup.sum == sum) Some(firstGroup) else None
  })

  println (d.minBy(_.product).product)

}



object Day23 {

  val input = Seq("jio a, +22", "inc a", "tpl a", "tpl a", "tpl a", "inc a", "tpl a", "inc a", "tpl a", "inc a", "inc a", "tpl a", "inc a", "inc a", "tpl a", "inc a", "inc a", "tpl a", "inc a", "inc a", "tpl a", "jmp +19", "tpl a", "tpl a", "tpl a", "tpl a", "inc a", "inc a", "tpl a", "inc a", "tpl a", "inc a", "inc a", "tpl a", "inc a", "inc a", "tpl a", "inc a", "tpl a", "tpl a", "jio a, +8", "inc b", "jie a, +4", "tpl a", "inc a", "jmp +2", "hlf a", "jmp -7")

  // case class Inc(register: Register)

  val Inc = """inc (\w)""".r
  val Tpl = """tpl (\w)""".r
  val Hlf = """hlf (\w)""".r
  val Jmp = """jmp ((\+|\-)\d*)""".r
  val Jio = """jio (\w), ((\+|\-)\d*)""".r
  val Jie = """jie (\w), ((\+|\-)\d*)""".r

  type Register = Int

  def recurse(m: Map[String, Register], pos: Int) : Map[String, Register] = {

    if (pos < 0 || pos >= input.length)
      return m

    input(pos) match {
      case Inc(reg) => recurse(m + (reg -> (m(reg) + 1)), 1+pos)
      case Tpl(reg) => recurse(m + (reg -> (m(reg) * 3)), 1+pos)
      case Hlf(reg) => recurse(m + (reg -> (m(reg) / 2)), 1+pos)
      case Jmp(off, _) => recurse(m, pos + off.toInt)
      case Jio(reg, off, _) if m(reg) == 1 => recurse(m, pos+off.toInt)
      case Jio(reg, off, _) => recurse(m, 1+pos)
      case Jie(reg, off, _) if m(reg) % 2 == 0 => recurse(m, pos+off.toInt)
      case Jie(reg, off, _) => recurse(m, 1+pos)
    }

  }

  val m = recurse(Map("a" -> 1, "b" -> 0), 0)

  println (m)

}