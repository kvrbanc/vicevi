package sel.zad.vicevi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ViceviApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViceviApplication.class, args);
	}
}
