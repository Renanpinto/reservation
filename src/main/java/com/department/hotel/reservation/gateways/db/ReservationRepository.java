package com.department.hotel.reservation.gateways.db;

import com.department.hotel.reservation.domains.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

}
