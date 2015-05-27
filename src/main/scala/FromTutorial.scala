import scala.util.parsing.combinator.JavaTokenParsers

/**
 * Created by zokni on 2015.05.26..
 */
class ReversePolishCalculator extends JavaTokenParsers {
  def num: Parser[Float] = floatingPointNumber ^^ (_.toFloat)
}