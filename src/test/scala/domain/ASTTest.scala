package domain

import org.scalatest._
import parsers.JsonParser
import printers.{CompactPrint, PrettyPrint}

/**
 * Created by maximribakov on 7/16/14.
 */

class ASTTest extends FlatSpec with Matchers {

  // Common objects --
  val person = JsonObject(
    JsonPair("name", JsonString("Max")),
    JsonPair("surname", JsonString("Hammer")),
    JsonPair("age", JsonInt(40))
  )
  val address = JsonObject(
    JsonPair("street1", JsonString("200 Larkin Street")),
    JsonPair("street2", JsonNull),
    JsonPair("city", JsonString("San Francisco")),
    JsonPair("state", JsonString("CA")),
    JsonPair("zip", JsonString("94102")),
    JsonPair("country", JsonString("United States"))
  )
  val order = JsonObject(
    JsonPair("from", person),
    JsonPair("to", address)
  )

  val twoHierarchy = JsonObject(
    JsonPair("from", person),
    JsonPair("to", JsonString("nothing"))
  )


  val f1 = JsonObject(JsonPair("name", JsonString("Max")))
  val f2 = JsonObject(JsonPair("surname", JsonString("Hammer")))
  val f3 = JsonObject(JsonPair("age", JsonInt(40)))

  "ASTTree" should "of be equal to the some of his parts" in {
    assert(f1 + f2 + f3 == person)

  }

  "ASTTree" should "print correctly empty vals" in {
    assert(JsonArray().getStringRep(CompactPrint) == "[]")
    assert(JsonArray().getStringRep(PrettyPrint()) == "[]")

    assert(JsonObject().getStringRep(CompactPrint) == "{}")
    assert(JsonObject().getStringRep(PrettyPrint()) == "{}")
  }

  "ASTTree" should "print one hierarchy correctly" in {
    assert(person.getStringRep(CompactPrint) == "{\"name\":\"Max\",\"surname\":\"Hammer\",\"age\":40}")
    assert(person.getStringRep(PrettyPrint(indentation = "  ")) ==
      """{
        |  "name":"Max",
        |  "surname":"Hammer",
        |  "age":40
        |}
      """.stripMargin.trim)
  }

  "ASTTree" should "print two hierarchy correctly" in {
    assert(twoHierarchy.getStringRep(CompactPrint) == """{"from":{"name":"Max","surname":"Hammer","age":40},"to":"nothing"}""")
    assert(twoHierarchy.getStringRep(PrettyPrint(indentation = "  ")) ==
      """{
        |  "from":{
        |    "name":"Max",
        |    "surname":"Hammer",
        |    "age":40
        |  },
        |  "to":"nothing"
        |}
      """.stripMargin.trim)

  }


//  "ASTHEll" should "work" in {
//    val blobPretty = """{
//                       |  "from":{
//                       |    "name":"Max",
//                       |    "surname":"Hammer",
//                       |    "age":40
//                       |  },
//                       |  "to":"nothing"
//                       |}
//                     """.stripMargin.trim
//    val blob = """{"from":{"name":"Max","surname":"Hammer","age":40},"to":"nothing"}"""
//    println(" JsonParser.parseJsonObject(blob).getStringRep(PrettyPrint(indentation = " + JsonParser.parseJsonObject(blob).getStringRep(PrettyPrint(indentation = "  ")))
//
//  }

}
