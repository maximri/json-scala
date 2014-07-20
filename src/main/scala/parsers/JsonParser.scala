package parsers

import domain._

import scala.collection.mutable.ListBuffer

/**
 * Created by maximr   ibakov on 7/16/14.
 * ddsdddds
 */
object JsonParser {

  def parseJsonPair(blob: String): JsonPair = {
    val key = blob.trim.substring(1,blob.indexOf(':')-1)
    val value = parseJsonValue(blob.substring(blob.indexOf(':')+1))

    JsonPair(key,value)
  }

  def parseJsonObject(blob: String) = {
    val splitByComma = blob.trim.drop(1).dropRight(1).split(',')

    var bracesBalanceCount = 0
    var partialBlob = ""
    val pairs = new ListBuffer[JsonPair]

    for (element <- splitByComma) {

      partialBlob = partialBlob + element

      bracesBalanceCount = bracesBalanceCount + element.count(_ == '{')
      bracesBalanceCount = bracesBalanceCount + element.count(_ == '[')
      bracesBalanceCount = bracesBalanceCount - element.count(_ == ']')
      bracesBalanceCount = bracesBalanceCount - element.count(_ == '}')

      if (bracesBalanceCount == 0) {
        pairs += parseJsonPair(partialBlob)
        partialBlob = ""
      }
    }
    JsonObject(pairs.toSeq:_*)
  }

  def parseJsonNumeric(blob: String) = {
    blob match {
    //  case isDouble => JsonDouble(blob.toDouble)
      case _ => JsonInt(blob.toInt)
    }
  }

  def parseJsonBoolean(blob: String) = {
    JsonBoolean(blob.asInstanceOf[Boolean])
  }

  def parseJsonNull(blob: String) = {
    JsonNull
  }

  def parseJsonString(blob: String) = {
    JsonString(blob)
  }

  def parseJsonArray(blob: String): JsonValue = {
    val splitByComma = blob.trim.drop(1).dropRight(1).split(',')

    var bracesBalanceCount = 0
    var partialBlob = ""
    val values = new ListBuffer[JsonValue]

    for (element <- splitByComma) {

      partialBlob = partialBlob + element

      bracesBalanceCount = bracesBalanceCount + element.count(_ == '{')
      bracesBalanceCount = bracesBalanceCount + element.count(_ == '[')
      bracesBalanceCount = bracesBalanceCount - element.count(_ == ']')
      bracesBalanceCount = bracesBalanceCount - element.count(_ == '}')

      if (bracesBalanceCount == 0) {
        values += parseJsonValue(partialBlob)
        partialBlob = ""
      }
    }
    JsonArray(values.toSeq:_*)
  }

  def parseJsonValue(value: String): JsonValue = {
    val onlyDigitsRegex = "^\\d*$".r

    value.head match {
      case '\"' => parseJsonString(value)
      case '[' => parseJsonArray(value)
      case '{' => parseJsonObject(value)
      case onlyDigitsRegex() => parseJsonNumeric(value)
      case 'f' | 't' => parseJsonBoolean(value)
      case 'n' => parseJsonNull(value)
      case '_' => parseJsonString(value)
    }
  }
}
