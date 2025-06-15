package put.text_transformer;

import put.text_transformer.functions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Registry that maps string action names to their corresponding text decorators.
 * Provides functionality to apply multiple decorators to a base TextFunction.
 */
public class DecoratorRegistry {

    /** Mapping from action name to decorator function. */
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

    /**
     * Applies a sequence of decorators (actions) to the given base TextFunction.
     *
     * @param base the initial TextFunction to decorate.
     * @param actions an iterable of action names specifying which decorators to apply.
     * @return a TextFunction wrapped with all the specified decorators applied in order.
     */
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
