package domain

import printers.{CompactPrint, PrettyPrint, PrintStrategy}


/**
 * Created by maximribakov on 7/15/14.
 */
case class JsonObject(pairs: JsonPair*) extends JsonValue {

  var myPairs = pairs

  override def getStringRep(strategy: PrintStrategy):String = {
    strategy match {
      case PrettyPrint(indentationCount,indentation) =>
        if (pairs.size <= 1) {
          myPairs.map(pair => pair.getStringRep(strategy)).mkString("{",",","}")
        }
        else {
          val prefix: String = "\n" + indentation * (indentationCount + 1)

          myPairs.map(pair => pair.getStringRep(PrettyPrint(indentationCount + 1,indentation))).
            mkString("{" + prefix, "," + prefix, "\n" + indentation*indentationCount +"}")
        }
      case CompactPrint =>
        myPairs.map(pair => pair.getStringRep(strategy)).mkString("{",",","}")

    }
  }

  def getPairByKey(key :String): JsonPair = {
    myPairs.filter(_.key == key).head
  }

  def +( other: JsonObject ) = JsonObject( ( myPairs ++ other.myPairs ):_* )
}
