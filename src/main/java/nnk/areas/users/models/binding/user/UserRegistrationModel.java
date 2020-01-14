package nnk.areas.users.models.binding.user;

import org.hibernate.validator.constraints.NotBlank;
import nnk.areas.users.annotations.FieldMatch;

import javax.validation.constraints.Pattern;

@FieldMatch
public class UserRegistrationModel {

    @Pattern(regexp = "^(?:\\S+)@(?:\\S+)\\.(?:\\S+)$", message = "Invalid Email")
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z -.]+$", message = "Invalid Name")
    private String fullName;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
