package com.strandum.domains;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Clocking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  clockInTime;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime clockOutTime;

}
