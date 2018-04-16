package homework.l3

case class PrsBook(title: String, author: String, genre: String, height: Int, publisher: String)

object TextParser {

  type Prs = Option[(String, String)]

  def parse(text: String): Seq[PrsBook] = {

    def makeBook(line: String): PrsBook = {
      val Some((title, end)) = parseField(line)
      val Some((author, end1)) = parseField(end)
      val Some((genre, end2)) = parseField(end1)
      val Some((height, end3)) = parseField(end2)
      val Some((publisher, end4)) = parseField(end3)

      assume(end4.isEmpty, "this cannot happen")

      PrsBook(title, author, genre, height.toInt, publisher)
    }

    def between(delim: Char, str: String): Prs =
      str match {
        case s if !s.startsWith(delim.toString) => None
        case _ =>
          val (fst, snd) = str.drop(1).span(_ != delim)
          Some((fst, snd.drop(2)))
    }

    def break(delim: Char, str: String): Prs =
      str match {
        case "" => None
        case _ =>
          val (fst, snd) = str.span(_ != delim)
          Some(fst, snd.drop(1))
      }

    def parseField(s: String): Prs =
      between('\"', s).orElse(break(',', s))

    text.split('\n').map(_.trim).filterNot(_.isEmpty).drop(1).map(makeBook)
  }
}
