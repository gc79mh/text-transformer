package put.text_transformer;

import java.util.List;

public class TransformResponse {
    private String text;
    private List<String> actions;
    private String result;

    public TransformResponse(String text, List<String> actions, String result) {
        this.text = text;
        this.actions = actions;
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public List<String> getActions() {
        return actions;
    }

    public String getResult() {
        return result;
    }
}
