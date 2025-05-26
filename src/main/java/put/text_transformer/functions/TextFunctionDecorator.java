package put.text_transformer.functions;

/**
 * An abstract base class for all text transformation decorators.
 * <p>
 * This class implements the {@link TextFunction} interface and wraps another
 * {@code TextFunction}, allowing additional behavior to be added before or
 * after the base function's {@code apply} method is executed.
 * </p>
 *
 * <p>
 * This is a key component of the Decorator design pattern used throughout the
 * text transformer system.
 * </p>
 */
public abstract class TextFunctionDecorator implements TextFunction {

    /**
     * The wrapped {@link TextFunction} instance to which this decorator delegates.
     */
    protected TextFunction wrappedFunction;

    /**
     * Constructs a {@code TextFunctionDecorator} with the specified base function.
     *
     * @param textFunction the {@code TextFunction} to wrap
     */
    public TextFunctionDecorator(TextFunction textFunction) {
        this.wrappedFunction = textFunction;
    }

    /**
     * Applies the wrapped {@code TextFunction}.
     * <p>
     * Subclasses typically override this method to add their own behavior
     * before or after calling {@code wrappedFunction.apply(text)}.
     * </p>
     *
     * @param text the input text
     * @return the result of the wrapped function's {@code apply} method
     */
    @Override
    public String apply(String text) {
        return wrappedFunction.apply(text);
    }
}
