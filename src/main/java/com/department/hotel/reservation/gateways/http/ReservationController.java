package com.department.hotel.reservation.gateways.http;

import com.department.hotel.reservation.domains.Reservation;
import com.department.hotel.reservation.gateways.http.json.ReservationJson;
import com.department.hotel.reservation.usecases.ReservationCrud;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservations")
public class ReservationController {

  private final ReservationCrud crud;

  @GetMapping
  @ApiOperation(
      value = "Find all Reservations")
  @ResponseStatus(value = HttpStatus.OK)
  public List<Reservation> findAll() {
    return crud.listReservations();
  }

  @GetMapping("/{id}")
  @ApiOperation(
      value = "Find reservation by Id")
  @ResponseStatus(value = HttpStatus.OK)
  public Reservation findById(@PathVariable String id) {
    return crud.listById(id);
  }

  @PostMapping
  @ApiOperation(
      value = "Creates a new reservation")
  @ResponseStatus(value = HttpStatus.CREATED)
  public Reservation create(
      @RequestBody @Valid ReservationJson reservationRequest) {
    return crud.create(reservationRequest.toDomain());
  }

  @PutMapping(value = "/{id}")
  @ApiOperation(
      value = "Update a reservation")
  @ResponseStatus(value = HttpStatus.OK)
  public Reservation update(
      @PathVariable String id,
      @RequestBody @Valid ReservationJson reservationRequest) {
    var reservation = reservationRequest.toDomain();
    reservation.setId(id);
    return crud.update(reservation);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(
      value = "Delete a reservation")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    crud.delete(id);
  }

}
