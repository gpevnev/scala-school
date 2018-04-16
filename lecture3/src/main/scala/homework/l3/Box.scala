package homework.l3

sealed trait Box
case class PlayStationBox() extends Box
case class GuitarBox() extends Box
case class EaselBox() extends Box
case class BasicBox() extends Box {
  val size: Int = 4
}
case class BigBox() extends Box {
  val size: Int = 10
}

trait Stuff
case class PlayStation() extends Stuff
case class Guitar() extends Stuff
case class TV(size: Int) extends Stuff
case class Easel() extends Stuff
case class Book() extends Stuff
case class Cat() extends Stuff
case class Uculele() extends Stuff
case class Dish() extends Stuff
case class Shoes() extends Stuff

object BoxPlan {

  def plan(stuff: Seq[Stuff]): Seq[Box] = {

    val packSingle: PartialFunction[Stuff, Box] = {
        case PlayStation() => PlayStationBox()
        case Guitar() => GuitarBox()
        case Easel() => EaselBox()
      }

    def packBatched(stuffs: Seq[Stuff]): Seq[Box] = {

      def transform(s: Seq[Int], sizeLeft: Int): Seq[Int] = {
        if (sizeLeft <= 0) {
          s
        } else {
          s match {
            case Seq(head: Int, tail@_*) =>
              transform(tail, sizeLeft - head)
            case Seq() =>
              Seq()
          }
        }
      }

      def mkBoxes(s: Seq[Int]): Seq[Box] = s match {
        case Seq() => Seq()
        case _     =>
          val box =
            if (s.head > BasicBox().size) {
              BigBox()
            } else {
              BasicBox()
            }

          val boxSize =
            if (s.head > BasicBox().size) {
              BigBox().size
            } else {
              BasicBox().size
            }

          val next = transform(s, boxSize)

          box +: mkBoxes(next)
        }

      val srt = stuffs
        .map({case tv: TV => tv.size; case _ => 1;})
        .sortBy(x => -x)

      mkBoxes(srt)
    }

    val (singles, batched) = stuff.partition(packSingle.isDefinedAt)

    singles.map(packSingle) ++ packBatched(batched)
  }
}