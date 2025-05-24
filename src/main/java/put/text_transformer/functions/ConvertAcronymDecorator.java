package put.text_transformer.functions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertAcronymDecorator extends TextFunctionDecorator {

    private static final Map<Pattern, String> ACRONYMS = new LinkedHashMap<>();
    static {
        ACRONYMS.put(Pattern.compile("\\bfor example\\b", Pattern.CASE_INSENSITIVE), "e.g.");
        ACRONYMS.put(Pattern.compile("\\bamong others\\b", Pattern.CASE_INSENSITIVE), "i.a.");
        ACRONYMS.put(Pattern.compile("\\band so on\\b", Pattern.CASE_INSENSITIVE), "aso");
        ACRONYMS.put(Pattern.compile("\\bthat is\\b", Pattern.CASE_INSENSITIVE), "i.e.");
        ACRONYMS.put(Pattern.compile("\\bnote well\\b", Pattern.CASE_INSENSITIVE), "N.B.");
        ACRONYMS.put(Pattern.compile("\\bcompare\\b", Pattern.CASE_INSENSITIVE), "cf.");
        ACRONYMS.put(Pattern.compile("\\bnamely\\b", Pattern.CASE_INSENSITIVE), "viz.");
        ACRONYMS.put(Pattern.compile("\\bin work cited\\b", Pattern.CASE_INSENSITIVE), "op. cit.");
        ACRONYMS.put(Pattern.compile("\\band others\\b", Pattern.CASE_INSENSITIVE), "et al.");
        ACRONYMS.put(Pattern.compile("\\bin the place cited\\b", Pattern.CASE_INSENSITIVE), "loc. cit.");
        ACRONYMS.put(Pattern.compile("\\bin the same place\\b", Pattern.CASE_INSENSITIVE), "ibid.");
        ACRONYMS.put(Pattern.compile("\\balso known as\\b", Pattern.CASE_INSENSITIVE), "aka");
        ACRONYMS.put(Pattern.compile("\\bprofessor\\b", Pattern.CASE_INSENSITIVE), "prof.");
        ACRONYMS.put(Pattern.compile("\\bdoctor\\b", Pattern.CASE_INSENSITIVE), "dr");
    }

    public ConvertAcronymDecorator(TextFunction textFunction) {
        super(textFunction);
    }

    @Override
    public String apply(String text) {
        String input = wrappedFunction.apply(text);
        if (input == null || input.isEmpty()) {
            return input;
        }
        String result = input;
        for (Map.Entry<Pattern, String> entry : ACRONYMS.entrySet()) {
            Matcher m = entry.getKey().matcher(result);
            result = m.replaceAll(entry.getValue());
        }
        return result;
    }
}