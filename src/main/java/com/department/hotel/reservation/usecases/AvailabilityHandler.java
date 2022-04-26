package com.department.hotel.reservation.usecases;

import com.department.hotel.reservation.domains.Reservation;
import com.department.hotel.reservation.gateways.ReservationGateway;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvailabilityHandler {

  private final ReservationGateway gateway;

  public List<Reservation> checkAvailability(LocalDate startDate, LocalDate endDate) {
    return gateway.findByRange(startDate, endDate);
  }

}
