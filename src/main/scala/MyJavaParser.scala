import scala.util.parsing.combinator.JavaTokenParsers

/**
 * Created by zokni on 2015.05.26..
 */
trait MyElem[T] {
  val data: T 
}
case class StringElem(data : String) extends MyElem[String]
case class FloatElem(data: Float) extends MyElem[Float]
case class MyLine(data : List[MyElem[_]])

class MyJavaParser extends JavaTokenParsers {
  def str : Parser[String] = "[a-zA-Z0-9@.,]*".r
  def sep : Parser[String] = ";".r
  def endl : Parser[String] = "(\n)?".r
  def elemek : Parser[List[MyElem[_]]] = rep(elem ~ sep) ~ elem ^^ (e => e._1.map( _._1 ).:+(e._2))

  def stringElem : Parser[StringElem] = "\"" ~ str  ~ "\"" ^^ (e => StringElem(e._1._2))
  def intElem : Parser[FloatElem] = decimalNumber ^^ (e => FloatElem(e.toFloat))

  def elem : Parser[MyElem[_]] = stringElem | intElem
  def line : Parser[MyLine] = elemek ~ endl ^^ (e => MyLine(e._1))
  def lines : Parser[List[MyLine]] = rep(line)
}
