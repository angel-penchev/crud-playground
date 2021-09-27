package dev.penchev.crudplayground.resourse;

import dev.penchev.crudplayground.models.UserCredentialsModel;
import dev.penchev.crudplayground.models.UserModel;
import dev.penchev.crudplayground.service.AuthenticationService;
import io.dropwizard.auth.AuthenticationException;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AuthenticationResource {

    final AuthenticationService authenticationService;

    public AuthenticationResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @POST
    @Path("/register")
    public String register(@Valid UserModel user) {
        return authenticationService.registerUser(user);
    }

    @POST
    @Path("/login")
    public String login(@Valid UserCredentialsModel userCredentials) throws AuthenticationException {
        return authenticationService.loginUser(userCredentials);
    }
}
