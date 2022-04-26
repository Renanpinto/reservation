package com.department.hotel.reservation.gateways.db;

import com.department.hotel.reservation.domains.Reservation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

  @Query(value = "SELECT u FROM Reservation u WHERE\n"
      + "(u.startDate BETWEEN ?1 AND ?2 ) OR \n"
      + "(u.endDate BETWEEN ?1 AND ?2)")
  List<Reservation> findByRange(LocalDate startDate, LocalDate endDate);
}
