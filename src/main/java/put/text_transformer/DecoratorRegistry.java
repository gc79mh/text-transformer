package put.text_transformer;

import put.text_transformer.functions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DecoratorRegistry {
    private static final Map<String, Function<TextFunction, TextFunction>> DECORATORS = new HashMap<>();

    static {
        DECORATORS.put("lower", LowerCaseDecorator::new);
        DECORATORS.put("upper", UpperCaseDecorator::new);
        DECORATORS.put("reverse", ReverseDecorator::new);
        DECORATORS.put("capitalize", CapitalizeDecorator::new);
        DECORATORS.put("number2words", NumberToWordsDecorator::new);
        DECORATORS.put("acronym", ConvertAcronymDecorator::new);
        DECORATORS.put("expand", ExpandAcronymDecorator::new);
        DECORATORS.put("latex", LatexDecorator::new);
        DECORATORS.put("dedup", DeduplicateDecorator::new);
    }

    public static TextFunction applyDecorators(TextFunction base, Iterable<String> actions) {
        TextFunction result = base;
        for (String action : actions) {
            Function<TextFunction, TextFunction> decorator = DECORATORS.get(action.toLowerCase());
            if (decorator != null) {
                result = decorator.apply(result);
            }
        }
        return result;
    }
}
