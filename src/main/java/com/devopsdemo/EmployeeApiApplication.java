package com.devopsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class EmployeeApiApplication {

    List<String> employeeList = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApiApplication.class, args);
    }

    @GetMapping
    public List<String> getEmployees() {
        return employeeList;
    }

    @PostMapping
    public String addEmployee(@RequestBody String name) {
        employeeList.add(name);
        return "Added: " + name;
    }
}
