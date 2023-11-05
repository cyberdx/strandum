package com.strandum.repositories;

import com.strandum.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeTestTest extends TestConfig {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testDatabaseInitialization() {
        assertNotNull(employeeRepository.findAll());
    }
}