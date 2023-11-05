package com.strandum.services;

import com.strandum.domains.Employee;
import com.strandum.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    @Override
    public Iterable<Employee> listOfAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(@Valid Employee employee) {
        employee = employeeRepository.save(employee);
        UserDetails newUser = User.withDefaultPasswordEncoder()
                .username(employee.getUsername())
                .password("admin")
                .roles(employee.getRole().name())
                .build();

        userDetailsManager.createUser(newUser);

        return employee;
    }
}
