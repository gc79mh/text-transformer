package put.text_transformer;

import org.springframework.web.bind.annotation.*;
import put.text_transformer.functions.*;

@RestController
@RequestMapping("/transform")
public class TransformController {

    @GetMapping
    public String transform(
            @RequestParam String text,
            @RequestParam String action) {

        // Start with base component
        TextFunction transformer = new BaseTextFunction();

        if ("lower".equalsIgnoreCase(action)) {
            transformer = new LowerCaseDecorator(transformer);
        } else if ("upper".equalsIgnoreCase(action)) {
            transformer = new UpperCaseDecorator(transformer);
        } else if ("reverse".equalsIgnoreCase(action)) {
            transformer = new ReverseDecorator(transformer);
        } else if ("capitalize".equalsIgnoreCase(action)) {
            transformer = new CapitalizeDecorator(transformer);
        } else if ("number2words".equalsIgnoreCase(action)) {
            transformer = new NumberToWordsDecorator(transformer);
        } else if ("acronym".equalsIgnoreCase(action)) {
            transformer = new ConvertAcronymDecorator(transformer);
        } else if ("expand".equalsIgnoreCase(action)) {
            transformer = new ExpandAcronymDecorator(transformer);
        } else if ("latex".equalsIgnoreCase(action)) {
            transformer = new LatexDecorator(transformer);
        } else if ("dedup".equalsIgnoreCase(action)) {
            transformer = new DeduplicateDecorator(transformer);
        }

        return transformer.apply(text);
    }

    @GetMapping("/chain")
    public String transformChain(
            @RequestParam String text,
            @RequestParam String[] actions) {

        TextFunction transformer = new BaseTextFunction();

        for (String action : actions) {
            if ("lower".equalsIgnoreCase(action)) {
                transformer = new LowerCaseDecorator(transformer);
            } else if ("upper".equalsIgnoreCase(action)) {
                transformer = new UpperCaseDecorator(transformer);
            } else if ("reverse".equalsIgnoreCase(action)) {
                transformer = new ReverseDecorator(transformer);
            } else if ("capitalize".equalsIgnoreCase(action)) {
                transformer = new CapitalizeDecorator(transformer);
            } else if ("number2words".equalsIgnoreCase(action)) {
                transformer = new NumberToWordsDecorator(transformer);
            } else if ("acronym".equalsIgnoreCase(action)) {
                transformer = new ConvertAcronymDecorator(transformer);
            } else if ("expand".equalsIgnoreCase(action)) {
                transformer = new ExpandAcronymDecorator(transformer);
            } else if ("latex".equalsIgnoreCase(action)) {
                transformer = new LatexDecorator(transformer);
            } else if ("dedup".equalsIgnoreCase(action)) {
                transformer = new DeduplicateDecorator(transformer);
            }
        }

        return transformer.apply(text);
    }
}