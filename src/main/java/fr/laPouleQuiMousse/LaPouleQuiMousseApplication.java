package fr.laPouleQuiMousse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
/**
 * Normalement EnableAutoConfiguration est compris dans l'annotation SpringBootApplication
 * mais intellij ne s'y retrouve pas avec l'annotation autowired
 * cf. https://stackoverflow.com/a/49793605/6699380
 */
@EnableAutoConfiguration
@EnableSpringConfigured
public class LaPouleQuiMousseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaPouleQuiMousseApplication.class, args);
	}

}
