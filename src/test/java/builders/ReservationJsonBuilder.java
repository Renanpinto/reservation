package builders;

import com.department.hotel.reservation.gateways.http.json.ReservationJson;
import java.time.LocalDate;

public class ReservationJsonBuilder {

  public static ReservationJson VALID(String email, String username) {
    return ReservationJson.builder()
        .startDate(LocalDate.now().plusMonths(1).withDayOfMonth(1))
        .endDate(LocalDate.now().plusMonths(1).withDayOfMonth(2))
        .email(email)
        .username(username)
        .build();
  }

  public static ReservationJson VALID_PUT(String email, String username) {
    return ReservationJson.builder()
        .startDate(LocalDate.now().plusMonths(1).withDayOfMonth(3))
        .endDate(LocalDate.now().plusMonths(1).withDayOfMonth(4))
        .email(email)
        .username(username)
        .build();
  }

}
