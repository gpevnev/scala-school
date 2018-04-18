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

  implicit def eqOpt[T](implicit eqs: Eq[T]) = new Eq[Option[T]] {
    override def eq(a: Option[T], b: Option[T]): Boolean =
      a.size == b.size && a.zip(b).forall((p : (T, T)) => p._1 === p._2)
  }

  implicit def eqSeq[T](implicit eqs: Eq[T]) = new Eq[Seq[T]] {
    override def eq(a: Seq[T], b: Seq[T]): Boolean =
      a.size == b.size && a.zip(b).forall((p : (T, T)) => p._1 === p._2)
  }

  implicit def eqMapString[K, V](implicit keq: Eq[K], veq: Eq[V]) = new Eq[Map[K, V]] {
    override def eq(a: Map[K, V], b: Map[K, V]): Boolean =
      a.size == b.size && a.zip(b).forall((p : ((K, V), (K, V))) => p._1._1 === p._2._1 && p._1._2 === p._2._2)
  }
}

object Main extends App {
  import Eq._
  println(Seq(Some(1)) === Seq(Some(1)))
}

