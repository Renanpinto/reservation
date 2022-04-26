package com.department.hotel.reservation.usecases;

import com.department.hotel.reservation.domains.Reservation;
import com.department.hotel.reservation.gateways.ReservationGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCRUD {

  private final ReservationGateway gateway;

  public List<Reservation> listReservations() {
    return gateway.findAll();
  }

  public Reservation create(Reservation reservation) {
    return gateway.save(reservation);
  }

  public Reservation update(Reservation reservation) {
    findById(reservation.getId());
    return gateway.save(reservation);
  }

  public void delete(String id) {
    var reservationDb = findById(id);
    gateway.delete(reservationDb.getId());
  }

  private Reservation findById(String id) {
    return gateway.findById(id).orElse(null);
  }

}
