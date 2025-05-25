package put.text_transformer;

import java.util.List;

public class TransformRequest {
    private String text;
    private List<String> actions;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }
}