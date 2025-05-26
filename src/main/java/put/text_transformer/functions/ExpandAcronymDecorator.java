package put.text_transformer.functions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A decorator class that expands acronyms in the input text into their full phrase equivalents.
 *
 * This class uses regular expressions to detect specific acronyms and replace them
 * with corresponding expanded forms. The case of the acronym's letters influences
 * the capitalization of the expansion. Follows the Decorator design pattern.
 */
public class ExpandAcronymDecorator extends TextFunctionDecorator {

    /**
     * A map of regex patterns to their corresponding full phrase expansions.
     * LinkedHashMap ensures predictable order of replacements.
     */
    private static final Map<Pattern, String> EXPANSIONS = new LinkedHashMap<>();

    static {
        EXPANSIONS.put(Pattern.compile("(?i)\\bprof\\.(?=\\W|$)"), "professor");
        EXPANSIONS.put(Pattern.compile("(?i)\\bdr\\b"), "doctor");
        EXPANSIONS.put(Pattern.compile("(?i)\\be\\.g\\.(?=\\W|$)"), "for example");
        EXPANSIONS.put(Pattern.compile("(?i)\\baso\\b"), "and so on");
        EXPANSIONS.put(Pattern.compile("(?i)\\bi\\.a\\.(?=\\W|$)"), "among others");
        EXPANSIONS.put(Pattern.compile("(?i)\\bi\\.e\\.(?=\\W|$)"), "that is");
        EXPANSIONS.put(Pattern.compile("(?i)\\bn\\.b\\.(?=\\W|$)"), "note well");
        EXPANSIONS.put(Pattern.compile("(?i)\\bcf\\.(?=\\W|$)"), "compare");
        EXPANSIONS.put(Pattern.compile("(?i)\\bviz\\.(?=\\W|$)"), "namely");
        EXPANSIONS.put(Pattern.compile("(?i)\\baka\\b"), "also known as");
    }

    /**
     * Constructs an ExpandAcronymDecorator that wraps a given TextFunction.
     *
     * @param textFunction the TextFunction to be decorated
     */
    public ExpandAcronymDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    /**
     * Applies the decorated TextFunction and expands recognized acronyms
     * in the resulting text into their full forms.
     *
     * The capitalization of the original acronym is preserved in the expansion.
     *
     * @param text the input text
     * @return the text with acronyms expanded
     */
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
                        String expansion = e.getValue();
                        String[] words = expansion.split(" ");

                        // Handle dotted acronyms (e.g., i.e., e.g.)
                        if (acro.contains(".")) {
                            String[] letters = acro.split("\\.");
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < words.length && i < letters.length; i++) {
                                char c = letters[i].isEmpty() ? ' ' : letters[i].charAt(0);
                                String word = words[i];
                                if (Character.isUpperCase(c)) {
                                    word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
                                }
                                sb.append(word).append(" ");
                            }
                            return sb.toString().trim();
                        } else {
                            // Handle acronyms without dots (e.g., aka, aso)
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < words.length; i++) {
                                String word = words[i];
                                if (i < acro.length() && Character.isUpperCase(acro.charAt(i))) {
                                    word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
                                }
                                sb.append(word).append(" ");
                            }
                            return sb.toString().trim();
                        }
                    });
        }
        return result;
    }
}
