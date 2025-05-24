package put.text_transformer.functions;

public class ReverseDecorator extends TextFunctionDecorator {
    public ReverseDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) return input;

        int len = input.length();
        boolean[] isUpper = new boolean[len];
        for (int i = 0; i < len; i++) {
            isUpper[i] = Character.isUpperCase(input.charAt(i));
        }

        StringBuilder reversed = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            reversed.append(Character.toLowerCase(input.charAt(i)));
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = reversed.charAt(i);
            result.append(isUpper[i] ? Character.toUpperCase(c) : c);
        }

        return result.toString();
    }
}
