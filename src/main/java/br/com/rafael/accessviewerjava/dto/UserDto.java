package br.com.rafael.accessviewerjava.dto;

import br.com.rafael.accessviewerjava.model.UserType;
import jakarta.validation.constraints.*;

public class UserDto {
    //não precisamos nem do balande nem do id
    //pode compiar da entity
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 92, message = "Name must be lower than 92 characters long")
    private String name;

    @NotEmpty(message = "Cpf cannot be empty")
    @Size(max = 11, message = "Cpf must be lower than 11 characters long")
    private String cpf;

    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 62, message = "Email must be lower than 62 characters long")
    @Email(message = "Email must be a valid e-mail")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Type cannot be null, must be COMMON, SELLER or ADMIN")
    private UserType type;

    //fazer Alt+insert para pegar os gets e sets
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
