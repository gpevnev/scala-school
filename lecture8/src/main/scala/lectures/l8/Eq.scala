package lectures.l8

trait Eq[-T] {
  def eq(a: T, b: T): Boolean
}

trait LowPriorityEq {
  implicit def eqAny[T]: Eq[T] = new Eq[T] {
    override def eq(a: T, b: T) = a == b
  }
}

object Eq extends LowPriorityEq {

  implicit class EqRef[T](val a: T) extends AnyVal {
    def ===(b: T)(implicit eq: Eq[T]) = eq.eq(a, b)
    def !==(b: T)(implicit eq: Eq[T]) = !eq.eq(a, b)
  }

//  implicit def eqOpt[T](implicit eqs: Eq[T]) = new Eq[Option[T]] {
//    override def eq(a: Option[T], b: Option[T]): Boolean = {
//      val it = a.zip(b)
//      if (it.isEmpty) {
//        false
//      } else {
//        it.forall((p : (T, T)) => p._1 === p._2)
//      }
//    }
//  }

  implicit def eqOpt[T](implicit eqs: Eq[T]) = new Eq[Option[T]] {
    override def eq(a: Option[T], b: Option[T]): Boolean =
      a.size == b.size && a.zip(b).forall((p : (T, T)) => p._1 === p._2)
  }

  implicit def eqSeq[T](implicit eqs: Eq[T]) = new Eq[Seq[T]] {
    override def eq(a: Seq[T], b: Seq[T]): Boolean =
      a.size == b.size && a.zip(b).forall((p : (T, T)) => p._1 === p._2)
  }

//  implicit def eqMapString[T](implicit eqs: Eq[T]) = new Eq[Map]
}

object Main extends App {
  import Eq._
  println(Seq(Some(1)) === Seq(Some(1)))
}

