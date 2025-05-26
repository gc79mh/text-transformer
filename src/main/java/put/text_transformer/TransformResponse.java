package put.text_transformer;

import java.util.List;

/**
 * Represents a response from API
 */
public class TransformResponse {
    /** Original input text. */
    private String text;

    /** List of transformation actions applied. */
    private List<String> actions;

    /** Transformed text. */
    private String result;

    /**
     * Constructs a new TransformResponse.
     *
     * @param text Original input text.
     * @param actions List of actions applied.
     * @param result Transformed text.
     */
    public TransformResponse(String text, List<String> actions, String result) {
        this.text = text;
        this.actions = actions;
        this.result = result;
    }

    /**
     * Returns the original input text.
     *
     * @return the original text.
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the list of transformation actions applied.
     *
     * @return list of actions.
     */
    public List<String> getActions() {
        return actions;
    }

    /**
     * Returns the transformed result text.
     *
     * @return transformed text.
     */
    public String getResult() {
        return result;
    }
}
