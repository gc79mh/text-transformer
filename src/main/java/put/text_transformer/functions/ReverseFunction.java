package put.text_transformer.functions;

public class ReverseFunction implements TextFunction {

    @Override
    public String apply(String text) {
        if (text == null || text.isEmpty()) return text;

        int len = text.length();
        boolean[] isUpper = new boolean[len];
        for (int i = 0; i < len; i++) {
            isUpper[i] = Character.isUpperCase(text.charAt(i));
        }

        StringBuilder reversed = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            reversed.append(Character.toLowerCase(text.charAt(i)));
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = reversed.charAt(i);
            result.append(isUpper[i] ? Character.toUpperCase(c) : c);
        }

        return result.toString();
    }
}
