package com.department.hotel.reservation.gateways.http.json;

import com.department.hotel.reservation.domains.Reservation;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReservationJson {

  @ApiModelProperty(value = "Attribute username", example = "john", required = true)
  @NotBlank(message = "Username cannot be empty")
  private String username;
  @ApiModelProperty(value = "Attribute email", example = "john@email.com", required = true)
  @NotBlank(message = "Email cannot be empty")
  private String email;
  @ApiModelProperty(value = "Attribute startDate", example = "2022-04-14", required = true)
  @Future(message = "StartDate must be after now")
  private LocalDate startDate;
  @ApiModelProperty(value = "Attribute endDate", example = "2022-04-15", required = true)
  @Future(message = "EndDate must be after now")
  private LocalDate endDate;

  public Reservation toDomain() {
    return Reservation.builder()
        .email(email)
        .username(username)
        .startDate(startDate)
        .endDate(endDate)
        .build();
  }
}
