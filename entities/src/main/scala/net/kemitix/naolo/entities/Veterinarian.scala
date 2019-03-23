package net.kemitix.naolo.entities

import java.util
import scala.collection.JavaConverters._

/**
  * A Veterinarian.
  */
object Veterinarian {

  /**
    * Create a new Veterinarian object.
    *
    * @param id              the Veterinarian ID
    * @param name            the Veterinarians Name
    * @param specialisations the Specialisations of the Veterinarian
    * @return a new Veterinarian
    */
    def create(
                id: Long,
                name: String,
                specialisations: util.Set[VetSpecialisation]
              ) =
      new Veterinarian(id, name, specialisations.asScala.toStream.toSet)

  /**
    * Constructor parsing ';' delimited VetSpecialisation values.
    *
    * @param id              the Veterinarian ID
    * @param name            the Veterinarians Name
    * @param specialisations the Specialisations of the Veterinarian as a ';' delimited String
    */
  def create(
              id: Long,
              name: String,
              specialisations: String
            ) =
    new Veterinarian(id, name, specStringToSet(specialisations))

  def create() = new Veterinarian()

  private def specStringToSet(specialisations: String) = {
    specialisations.split(";").filter(_.length > 0)
      .map(VetSpecialisation.valueOf)
      .toSet
  }

}

final class Veterinarian private (
                                   id: Long,
                                   name: String,
                                   specialisations: Set[VetSpecialisation]
                                 ) {

  def this(
            id: Long,
            name: String,
            specialisations: String) =
    this(id, name, Veterinarian.specStringToSet(specialisations))

  def this() = this(0, "", "")

  def getId: Long = id
  def getName: String = name
  def getSpecialisations: util.Set[VetSpecialisation] = specialisations.asJava
}