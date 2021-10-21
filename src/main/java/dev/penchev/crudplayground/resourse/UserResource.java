package dev.penchev.crudplayground.resourse;

import dev.penchev.crudplayground.models.UserModel;
import dev.penchev.crudplayground.service.UserService;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.PATCH;

import javax.annotation.security.RolesAllowed;
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
    @Path("/self/edit")
    public UserModel editByAuth(@Auth UserModel userModel, @Valid UserModel editedUser) {
        return userService.editUser(userModel.getId(), editedUser, false);
    }

    @PATCH
    @RolesAllowed({"administrator"})
    @Path("/{id}/edit")
    public UserModel editById(@PathParam("id") UUID id, @Valid UserModel editedUser) {
        return userService.editUser(id, editedUser, true);
    }

    @DELETE
    @Path("/self/delete")
    public String deleteByAuth(@Auth UserModel user) {
        userService.deleteUser(user.getId());
        return "User deleted successfully.";
    }

    @DELETE
    @RolesAllowed({"administrator", "moderator"})
    @Path("/{id}/delete/")
    public String deleteById(@PathParam("id") UUID id) {
        userService.deleteUser(id);
        return "User with id '${id}' deleted successfully.";
    }
}
