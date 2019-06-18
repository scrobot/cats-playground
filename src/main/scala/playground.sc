import cats.{Apply, Foldable, Functor}
import cats.kernel.{Monoid, Semigroup}
import cats.implicits._

Monoid[Map[String, Int]].combineAll(List(Map("a" → 1, "b" → 2), Map("a" → 3)))

Monoid[Map[String, Int]].combineAll(List())

val source = List("Cats", "is", "awesome")
val product = Functor[List].fproduct(source)(_.length).toMap

product.get("Cats").getOrElse(0)

val listOpt = Functor[List] compose Functor[Option]
listOpt.map(List(Some(1), None, Some(3)))(_ + 1)

Semigroup.combine(Option(1), None)

Apply[Option].tuple2(Some(1), Some(2))

Option.empty[Int]


val addArity2 = (a: Int, b: Int) ⇒ a + b
val addArity3 = (a: Int, b: Int, c: Int) ⇒ a + b + c

Apply[Option].map3(Some(1), Some(2), None)(addArity3).getOrElse()

Foldable[List].toList(List(1, 2, 3))
Foldable[Option].toList(Option(42))
Foldable[Option].toList(None)

def parseInt(s: String): Option[Int] =
  Either.catchOnly[NumberFormatException](s.toInt).toOption

Foldable[List].traverse_(List("a", "b", "c"))(parseInt)
Foldable[List].traverse_(List("1", "2", "3"))(parseInt)

List(Option(1), Option(2), Option(3)).traverse(identity)

List(Option(1), None, Option(3)).traverse(identity)

val left: Either[String, Int] = Either.left("Something went wrong")
left.map(_ + 1)
