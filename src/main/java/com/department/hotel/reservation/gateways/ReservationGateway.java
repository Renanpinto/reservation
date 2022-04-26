package com.department.hotel.reservation.gateways;

import com.department.hotel.reservation.domains.Reservation;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationGateway {

  List<Reservation> findAll();

  Optional<Reservation> findById(String id);

  List<Reservation> findByRange(LocalDate startDate, LocalDate toDate);

  Reservation save(Reservation reservation);

  void delete(String id);


}
