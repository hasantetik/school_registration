package com.school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@ComponentScan("com.school.service.CoursesService")
@SpringBootTest
class SchoolApplicationTests {

	@Test
	void contextLoads() {
	}

}
