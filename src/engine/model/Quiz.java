package engine.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Quiz {

    @JsonView(Views.Public.class)
    private long id;

    @JsonView(Views.Public.class)
    private String title;

    @JsonView(Views.Public.class)
    private String text;

    @JsonView(Views.Public.class)
    private String[] options;

    @JsonView(Views.Internal.class)
    private int answer;

    public Quiz() { };

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public int getAnswer() {
        return answer;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
