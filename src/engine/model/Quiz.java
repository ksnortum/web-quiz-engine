package engine.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private long id;

    @JsonView(Views.Public.class)
    @NotBlank(message = "Title is mandatory")
    private String title;

    @JsonView(Views.Public.class)
    @NotBlank(message = "Text is mandatory")
    private String text;

    @JsonView(Views.Public.class)
    @NotNull(message = "Options cannot be null")
    @Size(min = 2, message = "Must have at least two options")
    private String[] options;

    @JsonView(Views.Internal.class)
    private int[] answer;

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

    public int[] getAnswer() {
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

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format(
                "id=%d; title=\"%s\"; text=\"%s\"; options=%s; answer=%s",
                getId(),
                getTitle(),
                getText(),
                Arrays.toString(getOptions()),
                Arrays.toString(getAnswer())
        );
    }
}
