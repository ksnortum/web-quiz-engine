package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Text is mandatory")
    private String text;

    /** Account ID (email) */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String creator;

    @NotNull(message = "Options cannot be null")
    @Size(min = 2, message = "Must have at least two options")
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    public String getCreator() {
        return creator;
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

    public void setCreator(String creator) {
        this.creator = creator;
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
                "Quiz: id=%d; title=\"%s\"; text=\"%s\"; creator=\"%s\"; options=%s; answer=%s",
                getId(),
                getTitle(),
                getText(),
                getCreator(),
                Arrays.toString(getOptions()),
                Arrays.toString(getAnswer())
        );
    }
}
