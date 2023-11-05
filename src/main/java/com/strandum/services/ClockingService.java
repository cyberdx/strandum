package com.strandum.services;

import com.strandum.domains.Clocking;

import java.util.List;
import java.util.Optional;

public interface ClockingService {
    List<Clocking> getClockingForEmployee(Long employeeId);

    List<Clocking> getClockingForEmployee(String userName);

    Optional<Clocking> getClockingById(Long id);

    void deleteClockingById(Long id);

    Clocking addClocking(Clocking clocking, Long employeeId);
}
