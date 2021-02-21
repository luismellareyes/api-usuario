package cl.mella.usuarios;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j
public class ApiRestUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestUsuarioApplication.class, args);
	}

}
