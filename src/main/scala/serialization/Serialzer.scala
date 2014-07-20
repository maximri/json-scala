package serialization

import domain.{JsonInt, JsonObject, JsonPair, JsonString}
import parsers.JsonParser
import serialization.JsonSerializerObject.JsonSerializer

/**
 * Created by maximribakov on 7/20/14.
 */
object JsonSerializerObject {

  trait SerializeFromString[T <: Serializable] {
    def serializeFromString(blob: String): T
  }

  trait SerializeToString[T <: Serializable] {
    def serializeToString(t: T): String
  }

  trait JsonSerializer[T <: Serializable] extends SerializeFromString[T] with SerializeToString[T]
}

object Serializers {

  def serializedJsonUsage[T](someDomainObject: T)(implicit serializer: JsonSerializer[T]): Unit = {
    sendToSomeone(serializer.serializeToString(someDomainObject))

    def sendToSomeone(s: String) = {
      /* Sending somewhere */
    }
  }

  case class Person(name: String, age: Int) extends Serializable

  implicit object PersonSerializer extends JsonSerializer[Person] {
    def serializeFromString(blob: String): Person = {

      val parsedJsonObject: JsonObject = JsonParser.parseJsonObject(blob)
      val personName: JsonPair = parsedJsonObject.getPairByKey("name")
      val personAge: JsonPair = parsedJsonObject.getPairByKey("age")

      Person(personName.value.asInstanceOf[JsonString].value, personAge.value.asInstanceOf[JsonInt].value)
    }

    def serializeToString(person: Person): String = {
      val jsonPersonObject = JsonObject(JsonPair("name", JsonString(person.name)),
        JsonPair("age", JsonInt(person.age)))
      jsonPersonObject.getStringRep()
    }
  }
}
