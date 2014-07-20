package printers

/**
 * Created by maximribakov on 7/15/14.
 */
sealed trait PrintStrategy {
}
case object CompactPrint extends PrintStrategy { }

case class PrettyPrint(indentationCount : Int = 0, indentation:String = "\t") extends PrintStrategy{}