package lectures.l4

object Collection1 extends App {

  // Реализовать функцию, которая умножит все элементы на K
  def multiplyK(seq: Seq[Int], k: Int): Seq[Int] = seq.map(k * _)

  // Реализовать функцию, которая выберет только те числка, что делятся на К без остатка
  def devideOnK(seq: Seq[Int], k: Int): Seq[Int] = seq.filter(_ % k == 0)

  // Реализовать функцию, которая повторяет каждое число равное его значению раз, порядок сохраняется
  def numbers(seq: Int*): Seq[Int] = seq.flatMap(x => Seq.fill(x)(x))

  // Функция должна получить новый список, состоящий из попарных сумм, т.е. первый со вторым, второй с третьм и так далее
  def pairSum(seq: Int*): Seq[Int] = seq.zip(seq.tail).map(t => t._1 + t._2)

  println(multiplyK(Seq(1, 2, 3, 4, 5), 2)) // => 2 4 6 8 10

  println(devideOnK(Seq(0, 1, 2, 3, 4, 6), 3)) // => 0 3 6

  println(numbers(1, 2, 3)) // => 1, 2, 2, 3, 3, 3

  println(pairSum(1, 2, 3, 4)) // => 3, 5, 7
}
