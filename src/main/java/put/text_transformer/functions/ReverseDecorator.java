package put.text_transformer.functions;

/**
 * A decorator class that reverses the input text while preserving the case
 * of characters based on their original positions.
 * <p>
 * For example, "AbC!" would become "!cBa".
 * The character order is reversed, but the capitalization pattern from
 * the original text is preserved at the corresponding original positions.
 * </p>
 *
 * This class follows the Decorator design pattern and enhances the behavior
 * of a wrapped {@link TextFunction}.
 */
public class ReverseDecorator extends TextFunctionDecorator {

    /**
     * Constructs a ReverseDecorator that wraps a given TextFunction.
     *
     * @param textFunction the TextFunction to be decorated
     */
    public ReverseDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    /**
     * Applies the wrapped TextFunction and then reverses the resulting string.
     * The case of each character is determined by the case of the character
     * at the same index in the original (pre-reversed) string.
     *
     * @param text the input text
     * @return the reversed text with preserved original case pattern
     */
    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) return input;

        java.util.List<Boolean> isUpperList = new java.util.ArrayList<>();
        for (char c : input.toCharArray()) {
            if (c != ' ') {
                isUpperList.add(Character.isUpperCase(c));
            }
        }

        StringBuilder reversed = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(Character.toLowerCase(input.charAt(i)));
        }

        StringBuilder result = new StringBuilder();
        int caseIndex = 0;
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (c != ' ') {
                if (isUpperList.get(caseIndex)) {
                    c = Character.toUpperCase(c);
                }
                caseIndex++;
            }
            result.append(c);
        }

        return result.toString();
    }

}
