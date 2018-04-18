package homework.l4

/**
  * Неиспользуя мутабельные коллекции и var, реализовать сортировку слиянием.
  * Подробнее о сортировке можно подсмотреть здесь - https://en.wikipedia.org/wiki/Merge_sort
  *
  *
  */
object MergeSort {

  def mergeSort(data: Seq[Int]): Seq[Int] = {

    def merge(data1: Seq[Int], data2: Seq[Int]): Seq[Int] =
      (data1, data2) match {
        case (Seq(), right) => right
        case (left, Seq())  => left
        case (left@Seq(lhead, ltail@_*), right@Seq(rhead, rtail@_*)) =>
          if (lhead <= rhead)
            lhead +: merge(ltail, right)
          else
            rhead +: merge(left, rtail)
      }

    val m = data.size / 2
    m match {
     case 0 => data
     case _ =>
       val (part1, part2) = data.splitAt(m)
       merge(mergeSort(part1), mergeSort(part2))
    }
  }

}
