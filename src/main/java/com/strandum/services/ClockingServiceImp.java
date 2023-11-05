package com.strandum.services;

import com.strandum.domains.Clocking;
import com.strandum.domains.Employee;
import com.strandum.models.ProductMessageDto;
import com.strandum.repositories.ClockingRepository;
import com.strandum.repositories.EmployeeRepository;
import com.strandum.services.reporting.ProductProducer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ClockingServiceImp implements ClockingService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClockingRepository clockingRepository;
    @Autowired
    private ProductProducer productProducer;

    @Override
    public List<Clocking> getClockingForEmployee(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            return employee.getClockings();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Clocking> getClockingForEmployee(String userName) {
        return getClockingForEmployee(employeeRepository.findByUsername(userName).getId());
    }

    @Override
    public Optional<Clocking> getClockingById(Long id) {
        return clockingRepository.findById(id);
    }

    @Override
    public void deleteClockingById(Long id) {
        productProducer.send(new ProductMessageDto("userName", "content"));
        clockingRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Clocking addClocking(Clocking clocking, Long employeeId) {
        clocking.setEmployee(employeeRepository.findById(employeeId).get());
        productProducer.send(new ProductMessageDto("userName", "content"));
        return clockingRepository.save(clocking);
    }
}
