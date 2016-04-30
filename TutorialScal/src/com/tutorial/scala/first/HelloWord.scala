package com.tutorial.scala.first

import java.util.{Date, Locale}
import java.text.DateFormat._

object HelloWorld {
  def main(args: Array[String]) {
    val now = new Date
    val df = getDateInstance(LONG,Locale.FRANCE)
    var x = df format now
    println(x)
    println("Hello, world!")
  }
}