package domain

import printers.PrintStrategy

/**
 * Created by maximribakov on 7/16/14.
 */
sealed trait JsonPrimitive extends JsonValue

sealed trait JsonNumber extends JsonPrimitive

case class JsonInt(value: Int) extends JsonNumber {
  override def getStringRep(strategy: PrintStrategy): String = value.toString
}

case class JsonDouble(value: Double) extends JsonNumber {
  override def getStringRep(strategy: PrintStrategy): String = value.toString
}

case class JsonString(value: String) extends JsonPrimitive {
  override def getStringRep(strategy: PrintStrategy): String = "\"" + value + "\""
}
