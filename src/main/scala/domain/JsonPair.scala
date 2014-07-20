package domain

import printers.{PrintableJson, PrintStrategy, CompactPrint, PrettyPrint}

/**
 * Created by maximribakov on 7/15/14.
 */
case class JsonPair(key: String, value: JsonValue) extends PrintableJson {
  override def getStringRep(strategy: PrintStrategy) = {
    strategy match {
      case PrettyPrint(_,_) => "\"" + key +"\":" + value.getStringRep(strategy)
      case CompactPrint => "\"" + key+ "\":" + value.getStringRep(strategy)
    }
  }
}

