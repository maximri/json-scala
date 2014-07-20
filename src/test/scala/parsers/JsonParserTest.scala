package parsers

import org.scalatest.{FlatSpec, Matchers}
import printers.CompactPrint

/**
 * Created by maximribakov on 7/17/14.
 */
class JsonParserTest extends FlatSpec with Matchers {

  "JsonParser" should "parse correctly compact strings" in {
    val blobPretty = """{
                       |  "from":{
                       |    "name":"Max",
                       |    "surname":"Hammer",
                       |    "age":40
                       |  },
                       |  "to":"nothing"
                       |}
                     """.stripMargin.trim
    val blob = """{"from":{"name":"Max","surname":"Hammer","age":40},"to":"nothing"}"""

    JsonParser.parseJsonObject(blob).getStringRep(CompactPrint) === blob
  }
}
