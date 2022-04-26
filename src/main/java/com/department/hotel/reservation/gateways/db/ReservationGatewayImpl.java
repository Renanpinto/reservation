package com.department.hotel.reservation.gateways.db;

import com.department.hotel.reservation.domains.Reservation;
import com.department.hotel.reservation.gateways.ReservationGateway;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationGatewayImpl implements ReservationGateway {

  private final ReservationRepository repository;

  @Override
  public List<Reservation> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Reservation> findById(String id) {
    return repository.findById(id);
  }

  @Override
  public Reservation save(Reservation reservation) {
    return repository.save(reservation);
  }

  @Override
  public void delete(String id) {
    repository.deleteById(id);
  }
}
