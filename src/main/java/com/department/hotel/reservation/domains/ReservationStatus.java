package com.department.hotel.reservation.domains;

import java.time.LocalDate;

public enum ReservationStatus {
  ACTIVE,
  INACTIVE,
  EXPIRED;

  public static ReservationStatus checkStatus(Reservation reservation) {
    LocalDate actualTime = LocalDate.now();
    if (actualTime.isBefore(reservation.getStartDate())) {
      return ReservationStatus.INACTIVE;
    }
    if (actualTime.isAfter(reservation.getEndDate())) {
      return ReservationStatus.EXPIRED;
    }
    return ReservationStatus.ACTIVE;
  }
}
