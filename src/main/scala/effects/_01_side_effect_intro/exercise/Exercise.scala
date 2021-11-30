package effects._01_side_effect_intro.exercise

import effects._01_side_effect_intro.MyIO

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration

object Exercise extends App {
  // Write a clock action that returns the current time in milliseconds,
  // i.e., via System.currentTimeMillis.
  val clock: MyIO[Long] = MyIO(() => System.currentTimeMillis())

  // Write a timer that records the duration of another action.
  def time[A](action: MyIO[A]): MyIO[(FiniteDuration, A)] = for {
    start <- clock
    result <- action
    finish <- clock
  } yield (FiniteDuration(finish - start, TimeUnit.MILLISECONDS), result)

  val timedHello = time(MyIO.putStr("hello"))

  timedHello.unsafeRun() match {
    case (duration, _) => println(s"'hello' took $duration")
  }
}
