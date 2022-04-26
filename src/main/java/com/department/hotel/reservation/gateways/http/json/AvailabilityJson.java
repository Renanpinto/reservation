package com.department.hotel.reservation.gateways.http.json;

import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AvailabilityJson {

  private final boolean available = false;
  private final LocalDate startDate;
  private final LocalDate endDate;
}
