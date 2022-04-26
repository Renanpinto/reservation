package com.department.hotel.reservation.usecases.validation;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

import com.department.hotel.reservation.domains.Reservation;
import com.department.hotel.reservation.exceptions.BusinessException;
import com.department.hotel.reservation.gateways.ReservationGateway;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessValidator {

  private final ReservationGateway gateway;

  public void execute(Reservation reservation) {
    validateDates(reservation);
    validateAvailability(reservation);
  }

  private void validateAvailability(Reservation reservation) {
    var availableList = gateway.findByRange(reservation.getStartDate(), reservation.getEndDate());
    if (isNotEmpty(availableList)) {
      throw new BusinessException("The selected date is not available");
    }
  }

  private void validateDates(Reservation reservation) {
    if (reservation.getEndDate().isBefore(reservation.getStartDate())) {
      throw new BusinessException("Start date must be after End Date");
    }

    if (reservation.getEndDate().isAfter(reservation.getStartDate().plusDays(3))) {
      throw new BusinessException("The stay can’t be longer than 3 days");
    }

    if (reservation.getStartDate().isAfter(LocalDate.now().plusDays(30))) {
      throw new BusinessException("The stay can’t reserved more than 30 days in advance");
    }
  }

}
