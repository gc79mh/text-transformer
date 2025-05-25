package put.text_transformer;

import org.springframework.web.bind.annotation.*;
import put.text_transformer.functions.*;
import java.util.List;

@RestController
@RequestMapping("/transform")
public class TransformController {

    @GetMapping
    public TransformResponse transformGet(
            @RequestParam String text,
            @RequestParam List<String> actions) {
    
        TextFunction transformer = DecoratorRegistry.applyDecorators(new BaseTextFunction(), actions);

        String result = transformer.apply(text);
        return new TransformResponse(text, actions, result);
    }

    @PostMapping
    public TransformResponse transformPost(@RequestBody TransformRequest request) {
        String text = request.getText();
        List<String> actions = request.getActions();

        TextFunction transformer = DecoratorRegistry.applyDecorators(new BaseTextFunction(), actions);

        String result = transformer.apply(text);
        return new TransformResponse(text, actions, result);
    }
    
}