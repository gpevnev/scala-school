package homework.l4

/**
  * В этом домашнем задании вам нужно реализовать калькулятор.
  * Что он должен делать:
  * функция computeValues принимает Map, ключами являются имена переменных, а значением некоторое выражение.
  * На выходе функция возвращает Map, в которой все выражения вычислены.
  * Функция eval вычисляет одно заданное выражение, а getReferenceExpr возвращает значение переменной по имени.
  * Функция eval возвращает Double.NaN в случаях:
  * - переменная не была задана
  * - циклическая ссылка, например: a = c - b; b = a - 2
  */

sealed abstract class Expr
final case class Literal(v: Double) extends Expr
final case class Ref(name: String) extends Expr
final case class Plus(a: Expr, b: Expr) extends Expr
final case class Minus(a: Expr, b: Expr) extends Expr
final case class Times(a: Expr, b: Expr) extends Expr
final case class Divide(a: Expr, b: Expr) extends Expr

object Calculator {

  def computeValues(namedExpressions: Map[String, Expr]): Map[String, Double] =
    namedExpressions.mapValues(eval(_, namedExpressions))

  def eval(expr: Expr, references: Map[String, Expr]): Double = {

    def evalImpl(expr: Expr)(implicit usedRef: Set[String]): Double = expr match {
      case Literal(v) => v
      case Ref(name) =>
        if (usedRef.contains(name))
          Double.NaN
        else {
          evalImpl(getReferenceExpr(name, references))(usedRef + name)
        }
      case Plus(a, b) => evalImpl(a) + evalImpl(b)
      case Minus(a, b) => evalImpl(a) - evalImpl(b)
      case Times(a, b) => evalImpl(a) * evalImpl(b)
      case Divide(a, b) => evalImpl(a) / evalImpl(b)
    }

    evalImpl(expr)(Set.empty)
  }
  
  // Данная функция должна вернуть значение переменной по имени 
  def getReferenceExpr(name: String, references: Map[String, Expr]): Expr = references(name)

}
