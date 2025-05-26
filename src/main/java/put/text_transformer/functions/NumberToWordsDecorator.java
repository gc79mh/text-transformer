package put.text_transformer.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A decorator class that converts numeric values in the input text into their
 * corresponding English words representation.
 *
 * This class detects integers and decimal numbers, converting integers to words,
 * and decimals to a phrase expressing the integer part and hundredths fraction,
 * e.g., "123.45" becomes "one hundred twenty-three and forty-five hundredths".
 *
 * The decorator follows the Decorator design pattern by enhancing the output of a wrapped {@link TextFunction}.
 */
public class NumberToWordsDecorator extends TextFunctionDecorator {

    /**
     * Regex pattern that matches integers and optional fractional parts in the text.
     * It matches whole numbers and decimals such as "123" or "123.45".
     */
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\b(\\d+)(?:\\.(\\d+))?\\b");

    /**
     * Constructs a NumberToWordsDecorator that wraps a given TextFunction.
     *
     * @param textFunction the TextFunction to be decorated
     */
    public NumberToWordsDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    /**
     * Applies the decorated TextFunction and converts all numeric sequences in the result
     * into their word equivalents.
     *
     * The conversion handles integers up to 999,999 and decimals by expressing the integer
     * and hundredths parts in words.
     *
     * @param text the input text
     * @return the text with numeric values converted to words
     */
    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) return input;

        Matcher matcher = NUMBER_PATTERN.matcher(input);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String wholeStr = matcher.group(1);
            String fracStr = matcher.group(2);
            int whole = Integer.parseInt(wholeStr);

            String replacement;
            if (fracStr == null) {
                replacement = convertInteger(whole);
            } else {
                double value = Double.parseDouble(matcher.group());
                int intPart = (int) Math.floor(value);
                int hundredths = (int) Math.round((value - intPart) * 100);
                if (hundredths == 100) {
                    intPart++;
                    hundredths = 0;
                }
                String wordsInt = convertInteger(intPart);
                if (hundredths == 0) {
                    replacement = wordsInt;
                } else {
                    replacement = String.format("%s and %s hundredths",
                            wordsInt,
                            convertInteger(hundredths));
                }
            }

            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * Converts an integer number into its English words representation.
     * Supports numbers up to 999,999; above that, returns the number as a string.
     *
     * @param n the integer number to convert
     * @return the English words representation of the number
     */
    private String convertInteger(int n) {
        if (n == 0) return "zero";
        if (n >= 1_000_000) return convertAboveMillion(n);

        StringBuilder result = new StringBuilder();
        if (n >= 1000) {
            int thousands = n / 1000;
            result.append(convertBelowThousand(thousands));
            result.append(thousands > 1 ? " thousands" : " thousand");
            int rem = n % 1000;
            if (rem > 0) {
                result.append(" ").append(convertBelowThousand(rem));
            }
            return result.toString();
        }
        return convertBelowThousand(n);
    }

    /**
     * Converts numbers below 1000 into English words.
     *
     * @param n the number below 1000 to convert
     * @return the English words representation of the number
     */
    private String convertBelowThousand(int n) {
        String[] belowTwenty = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tensNames = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        StringBuilder w = new StringBuilder();
        if (n >= 100) {
            int h = n / 100;
            w.append(belowTwenty[h]).append(" hundred");
            n %= 100;
            if (n > 0) w.append(" ");
        }
        if (n >= 20) {
            int t = n / 10;
            w.append(tensNames[t]);
            int r = n % 10;
            if (r > 0) w.append("-").append(belowTwenty[r]);
        } else if (n > 0) {
            w.append(belowTwenty[n]);
        }
        return w.toString();
    }

    /**
     * Converts numbers above or equal to one million.
     * Currently, this method returns the numeric string representation without conversion.
     *
     * @param n the number to convert
     * @return the string representation of the number
     */
    private String convertAboveMillion(int n) {
        return Integer.toString(n);
    }
}
