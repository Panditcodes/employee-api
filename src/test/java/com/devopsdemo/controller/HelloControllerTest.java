package com.devopsdemo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.devopsdemo.EmployeeApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(classes = EmployeeApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloEndpointShouldReturnHello() {
        String response = this.restTemplate.getForObject("/hello", String.class);
        assertThat(response).isEqualTo("Hello from Employee API");

    }
}
