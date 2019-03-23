package net.kemitix.naolo.entities

import java.time.ZonedDateTime

/**
 * The Pet.
 */
final class Pet private(
                         val id: Long,
                         val name: String,
                         val dateOfBirth: ZonedDateTime,
                         val `type`: PetType,
                         val ownerId: Long
                       ) {
  def getId: Long = id
  def getName: String = name
  def getDateOfBirth: ZonedDateTime = dateOfBirth
  def getType: PetType = `type`
  def getOwnerId: Long = ownerId
}

object Pet {
  /**
    * Creates a new Pet object.
    *
    * @param id          the Pet ID
    * @param name        the Pets name
    * @param dateOfBirth the Pets Date of Birth
    * @param type        the type of Pet
    * @param ownerId     the Owners ID
    * @return a new Pet
    */
  def create(
              id: Long,
              name: String,
              dateOfBirth: ZonedDateTime,
              `type`: PetType,
              ownerId: Long
            ) =
    new Pet(id, name, dateOfBirth, `type`, ownerId)
}
