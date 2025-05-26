package put.text_transformer.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A decorator class that removes consecutive duplicate words from the input text.
 *
 * This class utilizes regular expressions to identify repeated word sequences
 * and replaces them with a single instance. It follows the Decorator design pattern
 * to enhance the behavior of a wrapped {@link TextFunction}.
 */
public class DeduplicateDecorator extends TextFunctionDecorator {

    /**
     * Regular expression pattern to match repeated words.
     *
     * It captures a word (non-whitespace sequence) and matches any number
     * of consecutive repetitions of that word separated by whitespace.
     */
    private static final Pattern REPEAT = Pattern.compile(
            "\\b([^\\s]+)\\b(?:\\s+\\1\\b)+",
            Pattern.UNICODE_CHARACTER_CLASS
    );

    /**
     * Constructs a DeduplicateDecorator that wraps a given TextFunction.
     *
     * @param textFunction the TextFunction to be decorated
     */
    public DeduplicateDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    /**
     * Applies the decorated TextFunction and removes consecutive duplicate words
     * from the resulting text.
     *
     * @param text the input text
     * @return the text with repeated words deduplicated
     */
    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) {
            return input;
        }
        Matcher m = REPEAT.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, Matcher.quoteReplacement(m.group(1)));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
