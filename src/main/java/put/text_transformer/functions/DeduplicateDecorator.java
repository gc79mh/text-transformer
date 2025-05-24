package put.text_transformer.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeduplicateDecorator extends TextFunctionDecorator {

    private static final Pattern REPEAT = Pattern.compile(
            "\\b([^\\s]+)\\b(?:\\s+\\1\\b)+",
            Pattern.UNICODE_CHARACTER_CLASS
    );

    public DeduplicateDecorator(TextFunction textFunction) {
        super(textFunction);
    }

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