package put.text_transformer;

import org.springframework.web.bind.annotation.*;
import put.text_transformer.functions.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST controller providing endpoints for text transformation.
 */
@RestController
@RequestMapping("/transform")
public class TransformController {

    private static final Logger logger = LoggerFactory.getLogger(TransformController.class);

    /**
     * Transforms the given text using the specified actions (GET request).
     *
     * @param text original input text.
     * @param actions list of transformation actions to apply.
     * @return the transformation response containing original text, actions, and result.
     */
    @GetMapping
    public TransformResponse transformGet(
            @RequestParam String text,
            @RequestParam List<String> actions) {

        logger.info("Received GET request with text='{}' and actions={}", text, actions);
        logger.debug("Applying transformations using DecoratorRegistry")
        TextFunction transformer = DecoratorRegistry.applyDecorators(new BaseTextFunction(), actions);

        String result = transformer.apply(text);
        logger.info("Transformation result='{}'", result);

        return new TransformResponse(text, actions, result);
    }

    /**
     * Transforms text based on the POSTed request body.
     *
     * @param request the transform request containing text and actions.
     * @return the transformation response.
     */
    @PostMapping
    public TransformResponse transformPost(@RequestBody TransformRequest request) {
        String text = request.getText();
        List<String> actions = request.getActions();

        logger.info("Received POST request with text='{}' and actions={}", text, actions);
        logger.debug("Applying transformations using DecoratorRegistry");
        TextFunction transformer = DecoratorRegistry.applyDecorators(new BaseTextFunction(), actions);

        String result = transformer.apply(text);
        logger.info("Transformation result='{}'", result);
        
        return new TransformResponse(text, actions, result);
    }
    
}