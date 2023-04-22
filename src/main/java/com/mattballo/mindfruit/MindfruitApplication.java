package com.mattballo.mindfruit;

import com.mattballo.mindfruit.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class MindfruitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindfruitApplication.class, args);
	}

}