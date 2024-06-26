package be.ucm.pocs.springboot.cucumber.steps.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "be.ucm.pocs.springboot.cucumber")
@EntityScan("be.ucm.pocs.springboot.cucumber.model")
@EnableJpaRepositories("be.ucm.pocs.springboot.cucumber.dao")
@EnableAutoConfiguration
public class TestConfig {
}
