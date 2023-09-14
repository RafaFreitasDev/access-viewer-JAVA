package br.com.rafael.accessviewerjava.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthDto {

    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 62, message = "Email must be lower than 62 characters long")
    @Email(message = "Email must be a valid e-mail")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
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
