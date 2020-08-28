package engine.model;

import com.fasterxml.jackson.annotation.JsonView;

public class QuizResponse {

    @JsonView(Views.Internal.class)
    private final boolean success;

    @JsonView(Views.Internal.class)
    private final String feedback;

    public QuizResponse(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
