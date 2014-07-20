package domain

import printers.PrintStrategy

/**
 * Created by maximribakov on 7/15/14.
 */
trait JsonLiteral extends JsonValue

case object JsonNull extends JsonLiteral {
  override def getStringRep(strategy: PrintStrategy): String = "null"
}

case class JsonBoolean( value: Boolean ) extends JsonLiteral{
  override def getStringRep(strategy: PrintStrategy): String = value.toString
}