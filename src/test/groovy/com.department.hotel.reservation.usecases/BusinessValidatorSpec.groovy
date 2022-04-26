package com.department.hotel.reservation.usecases

import builders.ReservationBuilder
import com.department.hotel.reservation.exceptions.BusinessException
import com.department.hotel.reservation.gateways.ReservationGateway
import com.department.hotel.reservation.usecases.validation.BusinessValidator
import spock.lang.Specification

import java.time.LocalDate

class BusinessValidatorSpec extends Specification {

    def reservationGateway = Mock(ReservationGateway)
    def validator = new BusinessValidator(reservationGateway)


    def "Should validate not valid requests to database"() {
        given: "A reservation request"
        def reservation = ReservationBuilder.VALID_WITH(startDate, endDate, email, username)

        and: "a request to database"
        reservationGateway.findById(_) >> Optional.of(reservation)

        when: "try to validate"
        validator.execute(reservation)

        then: 'exception must be thrown and verify message sent'
        def exception = thrown(Exception)
        exception.class == expectedException
        exception.message == message

        where: "The email and username are given below"
        startDate                    | endDate                      | email             | username   || expectedException | message
        LocalDate.now().plusDays(3)  | LocalDate.now().plusDays(2)  | "email@email.com" | "username" || BusinessException | "Start date must be after End Date"
        LocalDate.now().plusDays(1)  | LocalDate.now().plusDays(5)  | "email@email.com" | "username" || BusinessException | "The stay can’t be longer than 3 days"
        LocalDate.now().plusDays(31) | LocalDate.now().plusDays(32) | "email@email.com" | "username" || BusinessException | "The stay can’t be reserved more than 30 days in advance"
    }

    def "Should validate conflict dates for requests to database"() {
        given: "A reservation request"
        def reservation = ReservationBuilder.VALID("email@email.com", "username")

        and: "a request to database"
        reservationGateway.findById(_) >> Optional.of(reservation)
        reservationGateway.findByRange(_, _) >> [reservation]

        when: "try to validate"
        validator.execute(reservation)

        then: 'exception must be thrown and verify message sent'
        def exception = thrown(Exception)
        exception.class == BusinessException
        exception.message == "The selected date is not available"

    }

    def "Should validate valid requests to database"() {
        given: "A valid reservation request"
        def reservation = ReservationBuilder.VALID_WITH(startDate, endDate, email, username)

        and: "a request to database"
        def reservationDb = reservation
        reservationGateway.findByRange(_) >> reservationDb

        when: "try save reservation"
        validator.execute(reservation)

        then: 'no exceptions must be thrown'
        noExceptionThrown()

        where: "The email and username are given below"
        startDate                    | endDate                      | email             | username
        LocalDate.now().plusDays(1)  | LocalDate.now().plusDays(2)  | "email@email.com" | "username"
        LocalDate.now().plusDays(5)  | LocalDate.now().plusDays(6)  | "email@email.com" | "username"
        LocalDate.now().plusDays(10) | LocalDate.now().plusDays(12) | "email@email.com" | "username"
        LocalDate.now().plusDays(19) | LocalDate.now().plusDays(20) | "email@email.com" | "username"
    }

}
