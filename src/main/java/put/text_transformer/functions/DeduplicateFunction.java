package put.text_transformer.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeduplicateFunction implements TextFunction {

    // (?iu) – enable case-insensitive + Unicode character class
    // \b([^\\s]+)\b   – capture a “word” (any non-whitespace sequence)
    // (?:\s+\1\b)+    – one or more repetitions of that exact same word


    //CASE-INSENSITIVE - PLEASE Please please are the same words
//    private static final Pattern REPEAT = Pattern.compile(
//            "(?iu)\\b([^\\s]+)\\b(?:\\s+\\1\\b)+"
//    );

    //CASE-SENSITIVE - PLEASE Please please are different words
    private static final Pattern REPEAT = Pattern.compile(
            "\\b([^\\s]+)\\b(?:\\s+\\1\\b)+",
            Pattern.UNICODE_CHARACTER_CLASS
    );

    @Override
    public String apply(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        Matcher m = REPEAT.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, Matcher.quoteReplacement(m.group(1)));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
