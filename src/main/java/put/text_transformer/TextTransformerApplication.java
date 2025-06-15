package put.text_transformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entry point for the Text Transformer Spring Boot application.
 */
@SpringBootApplication
public class TextTransformerApplication {

     private static final Logger logger = LoggerFactory.getLogger(TextTransformerApplication.class);

	/**
     * Starts the Spring Boot application.
     *
     * @param args command line arguments (unused).
     */
	public static void main(String[] args) {
          logger.info("Starting TextTransformerApplication...");
		SpringApplication.run(TextTransformerApplication.class, args);
          logger.info("Application started.");
	}

}
