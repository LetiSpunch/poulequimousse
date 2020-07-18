package fr.laPouleQuiMousse.models;

import fr.laPouleQuiMousse.constraints.ValidPassword;

import javax.validation.constraints.NotNull;

public class UserNewPassword {
    @NotNull
    private String token;

    @NotNull
    @ValidPassword(message = "{users.save.error.invalidPassword}")
    private String password;

    @NotNull
    private String passwordConfirmation;

    public UserNewPassword(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
