package engine.model;

public class QuizWithAnswer extends Quiz {
    private int answer;

    public QuizWithAnswer() {
        super();
    }

    public QuizWithAnswer(long id, String title, String text, String[] options, int answer) {
        super(id, title, text, options);
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
