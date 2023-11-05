package com.strandum.repositories;

import com.strandum.domains.Clocking;
import org.springframework.data.repository.CrudRepository;

public interface ClockingRepository extends CrudRepository<Clocking, Long> {
}
