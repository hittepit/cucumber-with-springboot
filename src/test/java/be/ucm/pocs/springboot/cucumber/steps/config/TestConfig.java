package be.ucm.pocs.springboot.cucumber.steps.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("be.ucm.pocs.springboot.cucumber.dao")
@EntityScan("be.ucm.pocs.springboot.cucumber.model")
@ComponentScan(basePackages = "be.ucm.pocs.springboot.cucumber")
@EnableAutoConfiguration
public class TestConfig {
}
