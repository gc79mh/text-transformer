package put.text_transformer.functions;

public class LowerCaseDecorator extends TextFunctionDecorator {
    public LowerCaseDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    @Override
    public String apply(String text) {
        String result = wrappedFunction.apply(text);
        return result != null ? result.toLowerCase() : null;
    }
}