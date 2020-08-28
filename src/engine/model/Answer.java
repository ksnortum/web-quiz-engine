package engine.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Answer {

    @JsonView(Views.Internal.class)
    private int[] answer;

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

}
