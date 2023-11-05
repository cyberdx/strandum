package com.strandum.mock;

import com.strandum.constants.Roles;
import com.strandum.domains.Clocking;
import com.strandum.domains.Employee;
import com.strandum.repositories.ClockingRepository;
import com.strandum.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class DynamicUserAddition implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ClockingRepository clockingRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new ArrayList<>() {{
            add("admin");
            add("user1");
            add("user2");
        }}.forEach(u -> createMockUsers((String) u));
    }

    private void createMockUsers(String name) {
        Employee employee = new Employee();
        employee.setUsername(name);
        employee.setRole(name.equals("admin") ? Roles.ADMIN : Roles.USER);
        employee = employeeService.saveEmployee(employee);

        Clocking clocking = new Clocking();
        clocking.setClockInTime(LocalDateTime.now().minusHours(3));
        clocking.setClockOutTime(LocalDateTime.now());
        clocking.setEmployee(employee);
        employee.getClockings().add(clocking);

        clockingRepository.save(clocking);

    }
}
