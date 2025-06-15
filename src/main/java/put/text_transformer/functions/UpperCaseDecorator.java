package put.text_transformer.functions;

/**
 * A decorator that converts the result of a {@link TextFunction} to uppercase.
 * <p>
 * This class is part of the Decorator design pattern and adds uppercasing
 * behavior to the wrapped {@code TextFunction}.
 * </p>
 */
public class UpperCaseDecorator extends TextFunctionDecorator {

    /**
     * Constructs an {@code UpperCaseDecorator} that wraps the specified {@link TextFunction}.
     *
     * @param textFunction the {@code TextFunction} to wrap
     */
    public UpperCaseDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    /**
     * Applies the wrapped function and converts the result to uppercase.
     *
     * @param text the input text
     * @return the uppercased result of the wrapped function, or {@code null} if the result is {@code null}
     */
    @Override
    public String apply(String text) {
        String result = wrappedFunction.apply(text);
        return result != null ? result.toUpperCase() : null;
    }
}
