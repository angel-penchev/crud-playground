package dev.penchev.crudplayground.models;

public class UserJwtModel {
    private final String token;

    public UserJwtModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
