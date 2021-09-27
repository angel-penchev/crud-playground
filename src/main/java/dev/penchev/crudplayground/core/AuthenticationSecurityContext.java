package dev.penchev.crudplayground.core;

import dev.penchev.crudplayground.models.UserModel;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class AuthenticationSecurityContext implements SecurityContext {
    private final UserModel principal;
    private final SecurityContext securityContext;

    public AuthenticationSecurityContext(UserModel principal, SecurityContext securityContext) {
        this.principal = principal;
        this.securityContext = securityContext;
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String s) {
        // TODO: Fix user roles
        return true;
    }

    @Override
    public boolean isSecure() {
        return securityContext.isSecure();
    }

    @Override
    public String getAuthenticationScheme() {
        return "JWT";
    }

    @Override
    public String toString() {
        return "AuthenticationSecurityContext{" +
                "principal=" + principal +
                ", securityContext=" + securityContext +
                '}';
    }
}
