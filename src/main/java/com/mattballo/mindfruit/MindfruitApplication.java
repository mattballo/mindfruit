package com.mattballo.mindfruit;

import com.mattballo.mindfruit.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

// TODO: Vrat spravne OK/Error statusy
// TODO: throw custom 404 error from services
@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class MindfruitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindfruitApplication.class, args);
	}

}
