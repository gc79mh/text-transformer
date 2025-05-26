package put.text_transformer;

import java.util.List;

/**
 * Represents a request to the API
 */
public class TransformRequest {
    /** Text to be transformed. */
    private String text;

    /** List of transformation actions to apply. */
    private List<String> actions;

    /**
     * Returns the text to be transformed.
     *
     * @return the text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text to be transformed.
     *
     * @param text the input text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the list of actions to apply.
     *
     * @return list of actions.
     */
    public List<String> getActions() {
        return actions;
    }

    /**
     * Sets the list of actions to apply.
     *
     * @param actions list of transformation actions.
     */
    public void setActions(List<String> actions) {
        this.actions = actions;
    }
}