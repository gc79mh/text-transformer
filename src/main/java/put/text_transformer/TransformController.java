package put.text_transformer;

import org.springframework.web.bind.annotation.*;
import put.text_transformer.functions.*;

@RestController
@RequestMapping("/transform")
public class TransformController {

    @GetMapping
    public TransformResponse transformChain(
            @RequestParam String text,
            @RequestParam String[] action) {

        TextFunction transformer = new BaseTextFunction();

        for (String single_action : action) {
            if ("lower".equalsIgnoreCase(single_action)) {
                transformer = new LowerCaseDecorator(transformer);
            } else if ("upper".equalsIgnoreCase(single_action)) {
                transformer = new UpperCaseDecorator(transformer);
            } else if ("reverse".equalsIgnoreCase(single_action)) {
                transformer = new ReverseDecorator(transformer);
            } else if ("capitalize".equalsIgnoreCase(single_action)) {
                transformer = new CapitalizeDecorator(transformer);
            } else if ("number2words".equalsIgnoreCase(single_action)) {
                transformer = new NumberToWordsDecorator(transformer);
            } else if ("acronym".equalsIgnoreCase(single_action)) {
                transformer = new ConvertAcronymDecorator(transformer);
            } else if ("expand".equalsIgnoreCase(single_action)) {
                transformer = new ExpandAcronymDecorator(transformer);
            } else if ("latex".equalsIgnoreCase(single_action)) {
                transformer = new LatexDecorator(transformer);
            } else if ("dedup".equalsIgnoreCase(single_action)) {
                transformer = new DeduplicateDecorator(transformer);
            }
        }

        String result = transformer.apply(text);
        return new TransformResponse(text, action, result);
    }
}