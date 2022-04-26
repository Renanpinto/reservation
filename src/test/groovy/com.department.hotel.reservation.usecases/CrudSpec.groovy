package com.department.hotel.reservation.usecases

import builders.ReservationBuilder
import com.department.hotel.reservation.exceptions.NotFoundException
import com.department.hotel.reservation.gateways.ReservationGateway
import com.department.hotel.reservation.usecases.validation.BusinessValidator
import spock.lang.Specification

class CrudSpec extends Specification {

    def reservationGateway = Mock(ReservationGateway)
    def crud = new ReservationCrud(new BusinessValidator(reservationGateway), reservationGateway)

    def "Should create reservation in database with success"() {
        given: "A valid reservation request"
        def reservation = ReservationBuilder.VALID(email, username)

        and: "a request to database"
        def reservationDb = reservation
        reservationGateway.save(_) >> reservationDb

        when: "try save reservation"
        def result = crud.create(reservation)

        then: 'no exceptions must be thrown'
        noExceptionThrown()
        result == expectedReservation

        where: "The email and username are given below"
        email              | username   || expectedReservation
        "email@email.com"  | "username" || ReservationBuilder.VALID("email@email.com", "username")
        "email2@gmail.com" | "user"     || ReservationBuilder.VALID("email2@gmail.com", "user")
    }

    def "Should update and reservation in database with success"() {
        given: "A valid reservation request"
        def reservation = ReservationBuilder.VALID(email, username)

        and: "a request to database"
        def dbReservation = ReservationBuilder.VALID(previousEmail, previousUsername)
        def reservationDb = reservation
        reservationGateway.findById(_) >> Optional.of(dbReservation)
        reservationGateway.save(_) >> reservationDb

        when: "try save reservation"
        def result = crud.update(reservation)

        then: 'no exceptions must be thrown'
        noExceptionThrown()
        result == expectedReservation

        where: "The email and username are given below"
        previousEmail     | previousUsername | email             | username   || expectedReservation
        "test@test.com"   | "test"           | "email@email.com" | "username" || ReservationBuilder.VALID("email@email.com", "username")
        "email@email.com" | "username"       | "test@test.com"   | "test"     || ReservationBuilder.VALID("test@test.com", "test")
    }

    def "Should delete a reservation in database with success"() {
        given: "A valid delete request"
        def reservation = ReservationBuilder.VALID(email, username)

        and: "Reservation is found on db"
        reservationGateway.findById(_) >> Optional.of(reservation)

        when: "try delete reservation"
        crud.delete("id")

        then: 'no exceptions must be thrown'
        noExceptionThrown()

        where: "The email and username are given below"
        email             | username
        "email@email.com" | "username"
        "test@test.com"   | "test"
    }

    def "Should not delete a when is not present in db"() {
        given: "A valid reservation request"
        def reservation = ReservationBuilder.VALID(email, username)

        and: "Reservation is not found on db"
        reservationGateway.findById(_) >> Optional.empty()

        when: "try delete reservation"
        crud.delete("id")
        
        then: 'exception must be thrown and verify message sent'
        def exception = thrown(Exception)
        exception.class == expectedException
        exception.message == message

        where: "The email and username are given below"
        email             | username   || expectedException | message
        "email@email.com" | "username" || NotFoundException | "Reservation not found"
        "test@test.com"   | "test"     || NotFoundException | "Reservation not found"
    }

    def "Should list all reservations in database with success"() {
        given: "A valid list reservation request"
        def reservation = ReservationBuilder.VALID(email, username)

        and: "a request to database"
        reservationGateway.findAll() >> [reservation, reservation]

        when: "try to find reservation"
        def result = crud.listReservations()

        then: 'no exceptions must be thrown'
        noExceptionThrown()
        result == expectedReservation

        where: "The email and username are given below"
        email             | username   || expectedReservation
        "email@email.com" | "username" || [ReservationBuilder.VALID("email@email.com", "username"), ReservationBuilder.VALID("email@email.com", "username")]
        "test@test.com"   | "test"     || [ReservationBuilder.VALID("test@test.com", "test"), ReservationBuilder.VALID("test@test.com", "test")]
    }

    def "Should list a reservations by id in database with success"() {
        given: "A valid list reservation request"
        def reservation = ReservationBuilder.VALID(email, username)

        and: "a request to database"
        reservationGateway.findById(_) >> Optional.of(reservation)

        when: "try find reservation"
        def result = crud.listById("id")

        then: 'no exceptions must be thrown'
        noExceptionThrown()
        result == expectedReservation

        where: "The email and username are given below"
        email             | username   || expectedReservation
        "email@email.com" | "username" || ReservationBuilder.VALID("email@email.com", "username")
        "test@test.com"   | "test"     || ReservationBuilder.VALID("test@test.com", "test")
    }

    def "Should throw an NotFoundException when reservation is not found"() {
        given: "A valid list reservation request"
        def reservation = ReservationBuilder.VALID(email, username)

        and: "a request to database"
        reservationGateway.findById(_) >> Optional.empty()
        when: "try find reservation"
        crud.listById("id")

        then: 'exception must be thrown and verify message sent'
        def exception = thrown(Exception)
        exception.class == expectedException
        exception.message == message

        where: "The email and username are given below"
        email             | username   || expectedException | message
        "email@email.com" | "username" || NotFoundException | "Reservation not found"
        "test@test.com"   | "test"     || NotFoundException | "Reservation not found"
    }

}
