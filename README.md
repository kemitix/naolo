# Naolo

A Clean Architecture Example of the Pet Clinic using Jakarata EE 8.
  
Runtime and Integration Testing is provided using Quarkus.

Based loosely on the original 
[PetClinic](https://github.com/spring-projects/spring-petclinic) sample 
application from Spring.

## Purpose/Caveats

This project is both a work-in-progress and an experimental test-bed where I
play with the ideas of Clean Architecture and build my knowledge of Jakarta EE,
coming from a Spring background.

At this point, I wouldn't advocate using anything here are any suggestion of
how to do 'it'. I'm liable to refactor anything simply to explore how something
might work. As such, there may be some over-engineering as a result.

## Dependencies

Naolo uses Jarakta-EE 8.0 and as such the main business modules have that
as their only dependency. The exception is Lombok, because I want to avoid the 
noise of having lots of boiler-plate to maintain and parse for no reason.

The runtime and integration tests are based on Quarkus.

![Reactor Module Dependencies](./doc/images/reactor-graph.png)

> `make graphs`

## Pet Clinic

Rather that attempt to implement the full website from the original example, I
will be focusing on providing the services as a REST endpoint, upon which
another application/service could be used to provide the website.

## Domain Entities

The PetClinic has the following domain:

![Entities](./doc/images/entities.png)

> `make domain`
