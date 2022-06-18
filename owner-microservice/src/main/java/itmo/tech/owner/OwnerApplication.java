package itmo.tech.owner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("itmo.tech.owner.entity")
@ComponentScan({"itmo.tech.owner.service"})
@EnableJpaRepositories("itmo.tech.owner.repository")
@SpringBootApplication
public class OwnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OwnerApplication.class, args);
    }
}
