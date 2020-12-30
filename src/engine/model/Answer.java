package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Answer {

    @JsonIgnore
    private int[] answer;

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

}
