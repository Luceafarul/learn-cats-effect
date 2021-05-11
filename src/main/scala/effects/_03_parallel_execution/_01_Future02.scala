package effects._03_parallel_execution

import cats.implicits._
import scala.concurrent._
import scala.concurrent.duration._

object _01_Future02 extends App {
  implicit val ec: ExecutionContextExecutor = ExecutionContext.global

  def hello = Future(println(s"[${Thread.currentThread().getName}]: Hello"))
  def world = Future(println(s"[${Thread.currentThread().getName}]: World"))

  val hw01 = for {
    _ <- hello
    _ <- world
  } yield ()

  Await.ready(hw01, 5.seconds)

  val hw02 = (hello, world).mapN((_, _) => ())

  Await.ready(hw02, 5.seconds)
}
