package fr.laPouleQuiMousse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.laPouleQuiMousse.constraints.ValidPassword;
import fr.laPouleQuiMousse.models.Enums.ERoleName;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @Email
    @NotEmpty
    private String email;

    @Column(name = "password")
    private String encodedPassword;

    @ValidPassword
    @Transient
    private String password;

    @Transient
    private String passwordConfirmation;

    @Pattern(regexp = "[0-9]{10}", message = "{users.save.error.invalidPhone}")
    @NotEmpty
    private String phone;

    private Date firstActivationAt;

    private Date firstConnectionAt;

    @JsonIgnore
    private String token;

    @JsonIgnore
    private Date tokenExpirationDate;

    @JsonIgnore
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERoleName role;

    private Date lastLoginDate;

    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getFirstActivationAt() {
        return firstActivationAt;
    }

    public void setFirstActivationAt(Date firstActivationAt) {
        this.firstActivationAt = firstActivationAt;
    }

    public ERoleName getRole() {
        return role;
    }

    public void setRole(ERoleName role) {
        this.role = role;
    }

    public Date getFirstConnectionAt() {
        return firstConnectionAt;
    }

    public void setFirstConnectionAt(Date firstConnectionAt) {
        this.firstConnectionAt = firstConnectionAt;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpirationDate() {
        return tokenExpirationDate;
    }

    public void setTokenExpirationDate(Date tokenExpirationDate) {
        this.tokenExpirationDate = tokenExpirationDate;
    }

    public String getUsername() {
        return getFirstname() + " " + getLastname();
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }

    public boolean isFirstConnecting() {
        return firstConnectionAt == null;
    }
}