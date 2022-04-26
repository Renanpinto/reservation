package builders;

import com.department.hotel.reservation.domains.Reservation;
import java.time.LocalDate;

public class ReservationBuilder {

  public static Reservation VALID(String email, String username) {
    return Reservation.builder()
        .startDate(LocalDate.now().plusDays(1))
        .endDate(LocalDate.now().plusDays(2))
        .email(email)
        .username(username)
        .build();
  }

  public static Reservation VALID_WITH(
      LocalDate startDate, LocalDate endDate,
      String email, String username) {
    return Reservation.builder()
        .startDate(startDate)
        .endDate(endDate)
        .email(email)
        .username(username)
        .build();
  }

}
