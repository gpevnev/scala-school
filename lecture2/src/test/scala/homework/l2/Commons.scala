package homework.l2

import scala.util.Random.nextInt

object Commons {
  def randomList(n: Int): List[Int] =
    Stream.continually(nextInt(100)).take(10000).toList
}
