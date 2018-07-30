/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Paul Campbell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.kemitix.naolo.entities;

/**
 * The Owner of a Pet.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public final class Owner {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final String street;
    private final String city;
    private final String telephone;

    private Owner(
            final long id,
            final String firstName,
            final String lastName,
            final String street,
            final String city,
            final String telephone
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.telephone = telephone;
    }

    /**
     * Create a new Owner object.
     *
     * @param id the Owner ID
     * @param firstName the First Name
     * @param lastName the Last Name
     * @param street the Street address
     * @param city the City address
     * @param telephone the Telephone number
     * @return a new Owner
     */
    public static Owner create(
            final Long id,
            final String firstName,
            final String lastName,
            final String street,
            final String city,
            final String telephone
    ) {
        return new Owner(id, firstName, lastName, street, city, telephone);
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getTelephone() {
        return telephone;
    }
}
