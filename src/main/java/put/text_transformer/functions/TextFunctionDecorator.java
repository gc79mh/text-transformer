package put.text_transformer.functions;

public abstract class TextFunctionDecorator implements TextFunction {
    protected TextFunction wrappedFunction;

    public TextFunctionDecorator(TextFunction textFunction) {
        this.wrappedFunction = textFunction;
    }

    @Override
    public String apply(String text) {
        return wrappedFunction.apply(text);
    }
}