import scala.util.parsing.combinator._
import scala.util.parsing.combinator.Parsers
import scala.util.parsing.combinator.lexical._
import scala.util.parsing.combinator.lexical.Lexical
import scala.util.parsing.combinator.token._
import scala.util.parsing.combinator.token.StdTokens
import scala.util.parsing.combinator.token.Tokens
import scala.util.parsing.input.CharArrayReader._
import scala.util.parsing.combinator.token.StdTokens

/**
 * Created by zokni on 2015.05.12..
 */
class MyLexical extends Lexical with StdTokens with Parsers with Scanners {

  def token: Parser[Token] =
    ( identChar ~ rep( identChar | digit )              ^^ { case first ~ rest => processIdent(first :: rest mkString "") }
      | failure("illegal character")
      )

  protected def processIdent(name: String) = Keyword(name)


  def identChar = letter | elem('_')

  override def whitespace: Parser[Any] = rep(
    whitespaceChar
      | '/' ~ '/' ~ rep( chrExcept(EofCh, '\n') )
      | '/' ~ '*' ~ failure("unclosed comment")
  )
}
