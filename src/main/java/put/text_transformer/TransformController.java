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

        return text; // Fallback: no transformation
    }
}