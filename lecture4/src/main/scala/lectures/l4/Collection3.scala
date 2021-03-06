package lectures.l4

import scala.util.Random

object Collection3 extends App {

  // Задан некоторый список пассажиров М и Ж в слуйчайно количестве.
  // Необходимо распределить их по купэ, при этом не должно быть пустых и в каждом не больше 4х человек
  // В результате посчитать сколько купе какого типа получилось

  sealed trait Person

  case object Male extends Person

  case object Female extends Person

  case object AttackHelicopter extends Person

  sealed trait StateroomType

  case object MaleOnlyStateroom extends StateroomType

  case object FemaleOnlyStateroom extends StateroomType

  case object MixedStateroom extends StateroomType

  case class Stateroom(passengers: Seq[Person]) {
    require(passengers.length <= 4, "Stateroom overflow")
    require(passengers.nonEmpty, "Stateroom is empty")
  }

  def sort(list: Seq[Person]): Seq[Stateroom] = {
    val (m, f) = list.partition(_ == Male)
    val mixed = (Seq.fill(m.size % 4)(Male) ++ Seq.fill(f.size % 4)(Female)).splitAt(4)
    (Seq.fill[Stateroom](m.size / 4)(Stateroom(Seq.fill(4)(Male))) ++
      Seq.fill[Stateroom](f.size / 4)(Stateroom(Seq.fill(4)(Female))) ++
      Seq(Stateroom(mixed._1), Stateroom(mixed._2))).filterNot(_.passengers.isEmpty)
  }

  def toStateroomType(stateroom: Stateroom): StateroomType =
    stateroom.passengers match {
      case p if p.forall(_ == Collection3.Male) => MaleOnlyStateroom
      case p if p.forall(_ == Collection3.Female) => FemaleOnlyStateroom
      case _ => MixedStateroom
  }

  def count(list: Seq[Stateroom]): Map[StateroomType, Int] =
    list.map(x => toStateroomType(x) -> list.count(_ == x)).toMap

  val males = Seq.fill(Random.nextInt(20))(Male)
  val females = Seq.fill(Random.nextInt(20))(Female)
  val helicopters = Seq.fill(Random.nextInt(6))(AttackHelicopter)
  val passengers = Random.shuffle(males ++ females ++ helicopters)

  println(count(sort(passengers)))

}
