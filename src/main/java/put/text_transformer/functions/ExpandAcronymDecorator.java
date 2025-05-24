package put.text_transformer.functions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ExpandAcronymDecorator extends TextFunctionDecorator {

    private static final Map<Pattern, String> EXPANSIONS = new LinkedHashMap<>();
    static {
        EXPANSIONS.put(Pattern.compile("(?i)\\bprof\\.(?=\\W|$)"), "professor");
        EXPANSIONS.put(Pattern.compile("(?i)\\bdr\\.? (?=\\W|$)".replace(" ", "")), "doctor");
        EXPANSIONS.put(Pattern.compile("(?i)\\be\\.g\\.(?=\\W|$)"), "for example");
        EXPANSIONS.put(Pattern.compile("(?i)\\baso\\b"), "and so on");
        EXPANSIONS.put(Pattern.compile("(?i)\\bi\\.a\\.(?=\\W|$)"), "among others");
        EXPANSIONS.put(Pattern.compile("(?i)\\bi\\.e\\.(?=\\W|$)"), "that is");
        EXPANSIONS.put(Pattern.compile("(?i)\\bn\\.b\\.(?=\\W|$)"), "note well");
        EXPANSIONS.put(Pattern.compile("(?i)\\bcf\\.(?=\\W|$)"), "compare");
        EXPANSIONS.put(Pattern.compile("(?i)\\bviz\\.(?=\\W|$)"), "namely");
        EXPANSIONS.put(Pattern.compile("(?i)\\bop\\.\\s?cit\\.(?=\\W|$)"), "in work cited");
        EXPANSIONS.put(Pattern.compile("(?i)\\bet\\s?al\\.(?=\\W|$)"), "and others");
        EXPANSIONS.put(Pattern.compile("(?i)\\bloc\\.\\s?cit\\.(?=\\W|$)"), "in the place cited");
        EXPANSIONS.put(Pattern.compile("(?i)\\bibid\\.(?=\\W|$)"), "in the same place");
        EXPANSIONS.put(Pattern.compile("(?i)\\baka\\b"), "also known as");
    }

    public ExpandAcronymDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) {
            return input;
        }

        String result = input;
        for (Map.Entry<Pattern, String> e : EXPANSIONS.entrySet()) {
            result = e.getKey()
                    .matcher(result)
                    .replaceAll(match -> {
                        String acro = match.group();

                        boolean cap = Character.isUpperCase(acro.charAt(0));
                        String full = e.getValue();
                        if (cap) {
                            full = Character.toUpperCase(full.charAt(0)) + full.substring(1);
                        }
                        return full;
                    });
        }
        return result;
    }
}
