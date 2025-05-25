package put.text_transformer;

public class TransformResponse {
    private String original;
    private String[] actions;
    private String result;

    public TransformResponse(String original, String[] actions, String result) {
        this.original = original;
        this.actions = actions;
        this.result = result;
    }

    public String getOriginal() {
        return original;
    }

    public String getResult() {
        return result;
    }

    public String[] getActions() {
        return actions;
    }
}
