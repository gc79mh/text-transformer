package put.text_transformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Text Transformer Spring Boot application.
 */
@SpringBootApplication
public class TextTransformerApplication {

	/**
     * Starts the Spring Boot application.
     *
     * @param args command line arguments (unused).
     */
	public static void main(String[] args) {
		SpringApplication.run(TextTransformerApplication.class, args);
	}

}
