package net.kemitix.naolo.entities

import java.time.ZonedDateTime

/**
  * A Visit by a Pet with a Veterinarian.
  */
object Visit {
  /**
    * Create a new Visit object.
    *
    * @param id          the Visit ID
    * @param petId       the Pet ID
    * @param vetId       the Veterinarian ID
    * @param dateTime    the Date and Time of the Visit
    * @param description the Description
    * @return a new Visit
    */
    def create(
                id: Long,
                petId: Long,
                vetId: Long,
                dateTime: ZonedDateTime,
                description: String
              ) =
      new Visit(id, petId, vetId, dateTime, description)
}

final class Visit private(
                           val id: Long,
                           val petId: Long,
                           val vetId: Long,
                           val dateTime: ZonedDateTime,
                           val description: String
                         ) {
  def getId: Long = id
  def getPetId: Long = petId
  def getVetId: Long = vetId
  def getDateTime: ZonedDateTime = dateTime
  def getDescription: String = description
}