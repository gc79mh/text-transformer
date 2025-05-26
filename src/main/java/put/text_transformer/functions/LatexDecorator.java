package put.text_transformer.functions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A decorator class that escapes special LaTeX characters in the input text.
 *
 * <p>This class is used to ensure that text containing characters that are reserved
 * or have special meaning in LaTeX (such as <code>$</code>, <code>&#37;</code>, <code>&amp;</code>, etc.)
 * are properly escaped, so that the resulting text can be safely embedded in LaTeX documents.</p>
 *
 * <p>It follows the Decorator design pattern by extending the functionality of a base
 * {@link TextFunction}.</p>
 */

public class LatexDecorator extends TextFunctionDecorator {

    /**
     * A mapping of regular expression patterns to their LaTeX-escaped replacements.
     * LinkedHashMap is used to preserve the insertion order of escape rules.
     */
    private static final Map<Pattern, String> ESCAPES = new LinkedHashMap<>();

    static {
        ESCAPES.put(Pattern.compile("\\$"), "\\\\$");
        ESCAPES.put(Pattern.compile("\\{"), "\\\\{");
        ESCAPES.put(Pattern.compile("}"),   "\\\\}");
        ESCAPES.put(Pattern.compile("%"),   "\\\\%");
        ESCAPES.put(Pattern.compile("#"),   "\\\\#");
        ESCAPES.put(Pattern.compile("&"),   "\\\\&");
        ESCAPES.put(Pattern.compile("_"),   "\\\\_");
        ESCAPES.put(Pattern.compile("\\^"), "\\\\textasciicircum{}");
        ESCAPES.put(Pattern.compile("~"),   "\\\\textasciitilde{}");
        ESCAPES.put(Pattern.compile("`"),   "\\\\textasciigrave{}");
    }

    /**
     * Constructs a LatexDecorator that wraps a given TextFunction.
     *
     * @param textFunction the TextFunction to be decorated
     */
    public LatexDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    /**
     * Applies the decorated TextFunction and escapes all LaTeX special characters
     * in the resulting text according to the predefined rules.
     *
     * @param text the input text
     * @return the LaTeX-escaped text
     */
    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) {
            return input;
        }

        String result = input;
        for (Map.Entry<Pattern, String> e : ESCAPES.entrySet()) {
            Matcher m = e.getKey().matcher(result);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                m.appendReplacement(sb, Matcher.quoteReplacement(e.getValue()));
            }
            m.appendTail(sb);
            result = sb.toString();
        }
        return result;
    }
}
