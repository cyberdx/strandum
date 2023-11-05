package com.strandum.services;

import com.strandum.domains.Employee;

public interface EmployeeService {
    Iterable<Employee> listOfAllEmployees();

    Employee saveEmployee(Employee employee);
}
