package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "completions")
public class Completion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long completionId;

    @NotBlank(message = "Account ID cannot be blank")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String accountId;

    @Min(value = 0, message = "Quiz ID must be zero or more")
    @Column(name = "quizId")
    private long id;

    @NotNull(message = "Complete at date/time is mandatory")
    private LocalDateTime completedAt;

    public long getCompletionId() {
        return completionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /** Quiz ID, not Completion ID */
    public long getId() {
        return id;
    }

    /** Quiz ID, not Completion ID */
    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Completion that = (Completion) o;

        return getCompletionId() == that.getCompletionId()
                && getId() == that.getId()
                && getAccountId().equals(that.getAccountId())
                && getCompletedAt().equals(that.getCompletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompletionId(), getAccountId(), getId(), getCompletedAt());
    }

    @Override
    public String toString() {
        return "Completion{" +
                "completionId=" + completionId +
                ", accountId='" + accountId + '\'' +
                ", id=" + id +
                ", completedAt=" + completedAt +
                '}';
    }
}
