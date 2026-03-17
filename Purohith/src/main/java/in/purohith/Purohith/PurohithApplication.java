package in.purohith.Purohith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PurohithApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurohithApplication.class, args);
	}

}
