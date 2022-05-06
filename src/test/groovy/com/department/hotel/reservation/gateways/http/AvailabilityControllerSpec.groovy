package com.department.hotel.reservation.gateways.http


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
class AvailabilityControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc

    def "when get is performed then the response has status 200 and content is empty"() {
        expect: "Status is 200 and the response is array of reservation"
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/availability/{startDate}/{endDate}", LocalDate.of(2022, 05, 06), LocalDate.of(2022, 05, 07)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
    }

    def "when get is performed then the response has status 200 and content is not empty"() {
        expect: "Status is 200 and the response is array of reservation"
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/availability/{startDate}/{endDate}", LocalDate.of(2022, 04, 01), LocalDate.of(2022, 06, 01)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
    }

}


