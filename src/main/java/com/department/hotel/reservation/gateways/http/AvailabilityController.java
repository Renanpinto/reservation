package com.department.hotel.reservation.gateways.http;

import com.department.hotel.reservation.gateways.http.json.AvailabilityJson;
import com.department.hotel.reservation.usecases.AvailabilityHandler;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/availability")
public class AvailabilityController {

  private final AvailabilityHandler availabilityHandler;

  @GetMapping("/{startDate}/{endDate}")
  @ApiOperation(
      value = "Check if the date selected is available")
  @ResponseStatus(value = HttpStatus.OK)
  public List<AvailabilityJson> findAll(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    var reservationList = availabilityHandler.checkAvailability(startDate, endDate);
    return reservationList.stream().map(
        reservation -> new AvailabilityJson(reservation.getStartDate(), reservation.getEndDate()))
        .collect(Collectors.toList());
  }

}
