package dev.penchev.crudplayground.resourse;

import dev.penchev.crudplayground.models.UserModel;
import dev.penchev.crudplayground.service.UserService;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.PATCH;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/users/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {
    final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/{id}")
    public UserModel getById(@PathParam("id") UUID id) {
        return userService.getUserById(id);
    }

    @GET
    @Path("/self")
    public UserModel getByAuth(@Auth UserModel user) {
        return user;
    }

    @PATCH
    @Path("/edit")
    public UserModel put(@Auth UserModel userModel, @Valid UserModel editedUser) {
        return userService.editUser(userModel.getId(), editedUser);
    }

    @DELETE
    @Path("/delete")
    public String delete(@Auth UserModel user) {
        userService.deleteUser(user.getId());
        return "User deleted successfully.";
    }
}
