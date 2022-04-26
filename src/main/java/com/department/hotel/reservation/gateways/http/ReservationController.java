package com.department.hotel.reservation.gateways.http;

import com.department.hotel.reservation.domains.Reservation;
import com.department.hotel.reservation.usecases.ReservationCRUD;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
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

  private final ReservationCRUD crud;

  @GetMapping
  @ApiOperation(
      value = "Find all Reservations")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      })
  @ResponseStatus(value = HttpStatus.OK)
  public List<Reservation> findAll() {
    return crud.listReservations();
  }

  @PostMapping
  @ApiOperation(
      value = "Creates a new reservation")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      })
  @ResponseStatus(value = HttpStatus.OK)
  public Reservation create(
      @RequestBody Reservation reservation) {
    return crud.create(reservation);
  }

  @PutMapping(value = "/{id}")
  @ApiOperation(
      value = "Update a reservation")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      })
  @ResponseStatus(value = HttpStatus.OK)
  public Reservation update(
      @PathVariable String id,
      @RequestBody Reservation reservation) {
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
