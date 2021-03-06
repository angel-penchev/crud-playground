package dev.penchev.crudplayground.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.security.Principal;

public class UserCredentialsModel implements Principal {
    @NotNull
    @JsonProperty
    private String username;

    @NotNull
    @JsonProperty
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return "user credentials";
    }
}
