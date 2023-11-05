package com.strandum.domains;

import com.strandum.constants.Roles;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(indexes = {
        @Index(columnList = "username")
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "UserName is mandatory")
    @Size(min = 2, max = 25, message = "Name should be from 2 characters up to 25")
    private String username;
    @Enumerated(EnumType.STRING)
    private Roles role = Roles.USER;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Clocking> clockings = new ArrayList<>();
}
