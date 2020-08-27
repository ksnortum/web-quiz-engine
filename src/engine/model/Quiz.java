package engine.model;

public class Quiz {
    private long id;
    private String title;
    private String text;
    private String[] options;

    public Quiz() { };

    public Quiz(long id, String title, String text, String[] options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public Quiz(QuizWithAnswer quizWithAnswer) {
        this.id = quizWithAnswer.getId();
        this.title = quizWithAnswer.getTitle();
        this.text = quizWithAnswer.getText();
        this.options = quizWithAnswer.getOptions();
    }

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
}
