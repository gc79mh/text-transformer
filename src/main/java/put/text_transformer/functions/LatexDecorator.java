package put.text_transformer.functions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexDecorator extends TextFunctionDecorator {

    private static final Map<Pattern,String> ESCAPES = new LinkedHashMap<>();
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
        ESCAPES.put(Pattern.compile("`"), "\\\\textasciigrave{}");
    }

    public LatexDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) {
            return input;
        }
        String result = input;
        for (Map.Entry<Pattern,String> e : ESCAPES.entrySet()) {
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