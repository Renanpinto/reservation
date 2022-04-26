package com.department.hotel.reservation.usecases;

import com.department.hotel.reservation.domains.Reservation;
import com.department.hotel.reservation.exceptions.NotFoundException;
import com.department.hotel.reservation.gateways.ReservationGateway;
import com.department.hotel.reservation.usecases.validation.BusinessValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCrud {

  private final BusinessValidator validator;
  private final ReservationGateway gateway;

  public List<Reservation> listReservations() {
    return gateway.findAll();
  }

  public Reservation listById(String id) {
    return findById(id);
  }

  public Reservation create(Reservation reservation) {
    validator.execute(reservation);
    return gateway.save(reservation);
  }

  public Reservation update(Reservation reservation) {
    validator.execute(reservation);
    findById(reservation.getId());
    return gateway.save(reservation);
  }

  public void delete(String id) {
    var reservationDb = findById(id);
    gateway.delete(reservationDb.getId());
  }

  private Reservation findById(String id) {
    return gateway.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
  }

}
