package printers

/**
 * Created by maximribakov on 7/15/14.
 */
trait PrintableJson {
  def getStringRep(strategy: PrintStrategy = CompactPrint) : String
}
