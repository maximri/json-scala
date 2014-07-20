package domain

import printers.{CompactPrint, PrettyPrint, PrintStrategy}

/**
 * Created by maximribakov on 7/15/14.
 */
case class JsonArray(elements: JsonValue*) extends JsonValue {

  var myElements = elements

  override def getStringRep(strategy: PrintStrategy): String = {
    strategy match {
      case PrettyPrint(indentationCount, indentation) =>
        if (myElements.size <= 1) {
          myElements.map(elem => elem.getStringRep(strategy)).mkString("[", ",", "]")
        }
        else {
          val prefix: String = "\n" + indentation * (indentationCount + 1)

          myElements.map(elem => elem.getStringRep(PrettyPrint(indentationCount + 1,indentation))).
              mkString("[" + prefix, "," + prefix, indentation * indentationCount + "]")
        }
      case CompactPrint =>
        myElements.map(elem => elem.getStringRep(strategy)).mkString("[", ",", "]")
    }
  }
}
