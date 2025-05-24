package put.text_transformer.functions;

public class CapitalizeDecorator extends TextFunctionDecorator {
    public CapitalizeDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) return input;

        String[] words = input.split(" ");
        StringBuilder capitalizedText = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                capitalizedText.append(Character.toUpperCase(word.toLowerCase().charAt(0)))
                        .append(word.toLowerCase().substring(1)).append(" ");
            }
        }
        return capitalizedText.toString().trim();
    }
}