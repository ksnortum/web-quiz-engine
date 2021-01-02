package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Pattern(regexp = "^[^@]+@[^.]+\\..+$", message = "Invalid email")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Size(min = 5, message = "Password must be at least 5 in length")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}