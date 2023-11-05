package com.strandum.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig
@SpringBootTest
@TestPropertySource(properties = {
        "spring.jpa.generate-ddl=true",
        "spring.jpa.show-sql=true",
        "spring.jpa.hibernate.ddl-auto=create"
})
@TestConfiguration
public class TestConfig {

}
