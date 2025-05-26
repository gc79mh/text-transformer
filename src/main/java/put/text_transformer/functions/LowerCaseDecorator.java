package put.text_transformer.functions;

/**
 * A decorator class that converts all characters in the input text to lowercase.
 *
 * This class follows the Decorator design pattern and wraps another {@link TextFunction}
 * to extend its behavior by applying the {@code toLowerCase()} transformation
 * to the result of the wrapped function.
 */
public class LowerCaseDecorator extends TextFunctionDecorator {

    /**
     * Constructs a LowerCaseDecorator that wraps a given TextFunction.
     *
     * @param textFunction the TextFunction to be decorated
     */
    public LowerCaseDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    /**
     * Applies the decorated TextFunction and converts its result to lowercase.
     *
     * @param text the input text
     * @return the lowercased result of the decorated function, or {@code null} if input is null
     */
    @Override
    public String apply(String text) {
        String result = wrappedFunction.apply(text);
        return result != null ? result.toLowerCase() : null;
    }
}
