import scala.util.parsing.combinator._
import scala.util.parsing.combinator.lexical.Scanners
import scala.util.parsing.input.CharArrayReader

object Main extends App {

  case class Person(name: String, email: String, age: Int, isAlcoholic: Boolean)

  override def main(args: Array[String]) = {
    val src =
      """
        |"name";"email";"age";"isAlcoholic"
        |"Gergo";"gergo@example.org";10;1
        |"Zokni";"zokni@example.org";100;0.1
      """.stripMargin
    val parser = new MyJavaParser
    println(parser.parseAll(parser.lines, src.toCharArray))

  }
}
