package com.department.hotel.reservation.usecases

import builders.ReservationBuilder
import com.department.hotel.reservation.gateways.ReservationGateway
import spock.lang.Specification

class AvailabilityHandlerSpec extends Specification {

    def reservationGateway = Mock(ReservationGateway)
    def handler = new AvailabilityHandler(reservationGateway)


    def "Should check if there are any reservations for given dates"() {
        given: "A reservation request"
        def reservation = ReservationBuilder.VALID("email@email.com", "username")

        and: "a request to database"
        reservationGateway.findByRange(_, _) >> []

        when: "try to validate"
        def result = handler.checkAvailability(reservation.getStartDate(), reservation.getEndDate())

        then: 'no exceptions must be thrown'
        noExceptionThrown()
        result == []
    }

    def "Should check if there are any reservations for given dates occupied"() {
        given: "A reservation request"
        def reservation = ReservationBuilder.VALID("email@email.com", "username")

        and: "a request to database"
        reservationGateway.findByRange(_, _) >> [reservation]

        when: "try to validate"
        def result = handler.checkAvailability(reservation.getStartDate(), reservation.getEndDate())

        then: 'no exceptions must be thrown'
        noExceptionThrown()
        result != []
    }

}
