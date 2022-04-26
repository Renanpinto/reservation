package com.department.hotel.reservation.gateways;

import com.department.hotel.reservation.domains.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationGateway {

  List<Reservation> findAll();

  Reservation save(Reservation reservation);

  void delete(String id);

  Optional<Reservation> findById(String id);
}
