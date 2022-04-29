package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"controllers", "security"})
@EnableJpaRepositories("repository")
//@ComponentScan(basePackages = { "com.*"})
//@ComponentScan({"security", "repository", "controllers", "service"})
//@EntityScan("entity")
//@RestController
public class CrudSpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringDataJpaApplication.class, args);
    }

}