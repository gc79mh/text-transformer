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
        if ("lower".equalsIgnoreCase(action)) {
            return text.toLowerCase();
        } else if ("upper".equalsIgnoreCase(action)) {
            return text.toUpperCase();
        }
        else if ("reverse".equalsIgnoreCase(action)) {
            return new ReverseFunction().apply(text);
        } else if ("capitalize".equalsIgnoreCase(action)) {
            String[] words = text.split(" ");
            StringBuilder capitalizedText = new StringBuilder();
            for (String word : words) {
                if (word.length() > 0) {
                    capitalizedText.append(Character.toUpperCase(word.toLowerCase().charAt(0)))
                            .append(word.toLowerCase().substring(1)).append(" ");
                }
            }
            return capitalizedText.toString().trim();
        }
        else if ("number2words".equalsIgnoreCase(action)) {
            return new NumberToWordsFunction().apply(text);
        }
        else if ("acronym".equalsIgnoreCase(action)) {
            return new ConvertAcronymFunction().apply(text);
        }
        else if ("expand".equalsIgnoreCase(action)) {
            return new ExpandAcronymFunction().apply(text);
        } else if ("latex".equalsIgnoreCase(action)) {
            return new LatexFunction().apply(text);
        }
        else if ("dedup".equalsIgnoreCase(action)) {
            return new DeduplicateFunction().apply(text);
        }

        return text;
    }
}