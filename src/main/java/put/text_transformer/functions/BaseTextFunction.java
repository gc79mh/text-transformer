package put.text_transformer.functions;

public class BaseTextFunction implements TextFunction {
    @Override
    public String apply(String text) {
        return text;
    }
}