package serialization

/**
 * Created by maximribakov on 7/20/14.
 */
class PersonSerilization {

  def fromBinary[T <: Person](bytes: Array[Byte])
                            (implicit format: Format[T]): ActorRef = //..

  def toBinary[T <: Person](a: ActorRef)
                          (implicit format: Format[T]): Array[Byte] = //..


}
