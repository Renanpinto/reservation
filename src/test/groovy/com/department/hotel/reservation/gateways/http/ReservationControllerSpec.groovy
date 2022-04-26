package com.department.hotel.reservation.gateways.http


import builders.ReservationJsonBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
class ReservationControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc
    private ObjectMapper mapper = new ObjectMapper()

    def setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    def "when get is performed then the response has status 200 and content is empty"() {
        expect: "Status is 200 and the response is array of reservation"
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/reservations"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
    }

    def "when get by id is performed then the response id an reservation"() {
        expect: "Status is 200 and the response is a reservation "
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/reservations/{id}", 24))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
    }

    def "when create is performed the response has status 201 and response is an reservation"() {
        expect: "Status is 200 and the response is array of "
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ReservationJsonBuilder.VALID("email", "username"))))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn()
    }

    def "when update is performed the response is status 200 and response is an reservation"() {
        expect: "Status is 200 and the response is array of "
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/reservations/{id}", 24)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ReservationJsonBuilder.VALID_PUT("email", "username"))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
    }

    def "when delete is performed the response is status 204 and no response"() {
        expect: "Status is 200 and the response is array of "
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/reservations/{id}", 25)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ReservationJsonBuilder.VALID("email", "username"))))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn()
    }
}


