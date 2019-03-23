package net.kemitix.naolo.entities

/**
  * The Owner of a Pet.
  */
object Owner {
  /**
    * Create a new Owner object.
    *
    * @param id        the Owner ID
    * @param firstName the First Name
    * @param lastName  the Last Name
    * @param street    the Street address
    * @param city      the City address
    * @param telephone the Telephone number
    * @return a new Owner
    */
    def create(
                id: Long,
                firstName: String,
                lastName: String,
                street: String,
                city: String,
                telephone: String
              ) =
      new Owner(id, firstName, lastName, street, city, telephone)
}

final class Owner private(
                           val id: Long,
                           val firstName: String,
                           val lastName: String,
                           val street: String,
                           val city: String,
                           val telephone: String
                         ) {
  def getId: Long = id
  def getFirstName: String = firstName
  def getLastName: String = lastName
  def getStreet: String = street
  def getCity: String = city
  def getTelephone: String = telephone
}