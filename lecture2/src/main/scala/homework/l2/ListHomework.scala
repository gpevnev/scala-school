package homework.l2

import homework.l2.ListFunctions.fold

import scala.Function.uncurried


/**
  * Вам нужно реализовать функции sumOfSquares и multiplicationOfCubes
  * при помощи ListFunctions.fold, композиции и частичного применения функций, sum, multiply и pow
  * Можно добовлять промежуточные функции.
  * Также вам может понадобится функция Function.uncurry,
  * которая из карированной функции делает функцию с несколькими аргументами
  */

object ListHomework {

  val sum = (a: Int, b: Int) => a + b

  val multiply = (a: Int, b: Int) => a * b

  def pow(a: Int, p: Int): Int = if(p <= 0) 1 else a * pow(a, p - 1)

  val square: Int => Int = pow(_, 2)

  val cube: Int => Int = pow(_, 3)


  /**
    * Сумма квадратов чисел в списке
    */
  lazy val sumOfSquares: List[Int] => Int = (list: List[Int]) => {
   fold(0, list)(uncurried(sum.curried(_) compose square))
  }

  /**
    * Сумма кубов чисел в списке
    */
  lazy val multiplicationOfCubes: List[Int] => Int = (list: List[Int]) => {
    fold(1, list)(uncurried(cube andThen multiply.curried(_)))
  }

}
