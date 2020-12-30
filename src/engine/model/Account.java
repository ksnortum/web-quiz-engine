package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Pattern(regexp = "^[^@]+@[^.]+\\..+$")
    @Column(unique = true)
    @NotBlank(message = "Email cannot be null")
    private String email;

    //@Min(5)
    //@Length(...)
    @JsonIgnore
    @Size(min = 5, message = "Password must be at least 5 in length")
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