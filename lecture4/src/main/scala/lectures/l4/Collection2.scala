package lectures.l4

object Collection2 extends App {

  sealed trait Transport

  case object Auto extends Transport

  case class Truck(weight: Int) extends Transport

  // Реализовать функцию, выбирающую из списка только легковые машины и грузовики с весом меньше максимального
  // Релизовать тремя способами
  // - collect
  // - map/flatMap
  // - filter

  def collectorA(seq: Seq[Transport], maxWeight: Int): Seq[Transport] =
    seq.collect(PartialFunction[Transport, Transport]({
      case x: Truck if x.weight < maxWeight => x
      case Auto => Auto
    }))

  def collectorB(seq: Seq[Transport], maxWeight: Int): Seq[Transport] =
    seq.filterNot({
      case x: Truck if x.weight > maxWeight => true
      case _ => false
    })


  val transports  = Seq(
    Auto, Auto, Auto, Truck(100), Auto, Truck(150), Truck(110), Truck(200)
  )

  println(collectorA(transports, 120)) // => Auto, Auto, Auto, Track(100), Auto, Track(110)
  println(collectorB(transports, 120)) // => Auto, Auto, Auto, Track(100), Auto, Track(110)
}
