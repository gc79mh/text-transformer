package put.text_transformer.functions;

public class UpperCaseDecorator extends TextFunctionDecorator {
    public UpperCaseDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    @Override
    public String apply(String text) {
        String result = wrappedFunction.apply(text);
        return result != null ? result.toUpperCase() : null;
    }
}