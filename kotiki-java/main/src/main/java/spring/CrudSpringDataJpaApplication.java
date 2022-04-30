package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"controllers", "security"})
@EnableJpaRepositories("repository")

public class CrudSpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringDataJpaApplication.class, args);
    }

}