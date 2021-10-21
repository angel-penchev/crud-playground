package dev.penchev.crudplayground.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.penchev.crudplayground.core.PasswordUtilities;

import java.security.Principal;
import java.util.UUID;

public class UserModel implements Principal {
    private UUID id;
    private String username;
    private String password;
    private Role role;

    public UserModel() {}

    public UserModel(UUID id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @JsonCreator
    public UserModel(
        @JsonProperty("username") String username,
        @JsonProperty("password") String password
    ) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = PasswordUtilities.generateFromPassword(password);
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String getName() {
        return "user";
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
