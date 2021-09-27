package dev.penchev.crudplayground.models;

public class UserJwtModel {
    private String token;

    public UserJwtModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
