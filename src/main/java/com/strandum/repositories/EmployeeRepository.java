package com.strandum.repositories;

import com.strandum.domains.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByUsername(String userName);
}
