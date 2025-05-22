package put.text_transformer.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NumberToWordsFunction implements TextFunction {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\b(\\d+)(?:\\.(\\d+))?\\b");

    @Override
    public String apply(String text) {
        Matcher matcher = NUMBER_PATTERN.matcher(text);
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

    private String convertAboveMillion(int n) {
        return Integer.toString(n);
    }
}
