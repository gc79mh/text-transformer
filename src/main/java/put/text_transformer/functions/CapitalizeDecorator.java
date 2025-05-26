package put.text_transformer.functions;

/**
 * A decorator class that capitalizes the first letter of each word
 * in the input text, while converting the rest of the letters to lowercase.
 *
 * This class follows the Decorator design pattern, enhancing the functionality
 * of an existing TextFunction implementation.
 */
public class CapitalizeDecorator extends TextFunctionDecorator {

    /**
     * Constructs a CapitalizeDecorator that wraps a given TextFunction.
     *
     * @param textFunction the TextFunction to be decorated
     */
    public CapitalizeDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    /**
     * Applies the decorated TextFunction and capitalizes the first letter of
     * each word in the resulting text.
     *
     * @param text the input text
     * @return the capitalized version of the text
     */
    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) return input;

        String[] words = input.split(" ");
        StringBuilder capitalizedText = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                capitalizedText.append(Character.toUpperCase(word.toLowerCase().charAt(0)))
                        .append(word.toLowerCase().substring(1))
                        .append(" ");
            }
        }
        return capitalizedText.toString().trim();
    }
}
