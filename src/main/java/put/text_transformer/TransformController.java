package put.text_transformer;

import org.springframework.web.bind.annotation.*;

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
            return new StringBuilder(text).reverse().toString();
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
        return text;
    }
}