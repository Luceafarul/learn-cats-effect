package books.essential.effects.chapter01.examples

import books.essential.effects.chapter01.MyIO

object Printing extends App {
  // Describe the printing of "hello!" as a MyIO value.
  // But it has not been executed yet.
  val hello = MyIO.putStr("Hello, effects!")

  // Here we explicitly run side effect
  hello.unsafeRun()

  // Check substitution
  MyIO.putStr("Hello, effects!").unsafeRun()
  MyIO(() => println("Hello, effects!")).unsafeRun()

  // Replace the unsafeRun() expression with its definition,
  // which evaluates unsafeRun itself:
  println("Hello, effects!")
}
