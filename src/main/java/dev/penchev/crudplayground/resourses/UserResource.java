package dev.penchev.crudplayground.resourses;

import dev.penchev.crudplayground.database.UserDAO;
import dev.penchev.crudplayground.models.UserModel;
import dev.penchev.crudplayground.service.UserService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {
    final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/{id}")
    public UserModel get(@PathParam("id") int id){
        return userService.getUserById(id);
    }

    @POST
    public UserModel post(UserModel user) {
       return userService.createUser(user);
    }

    @PUT
    @Path("/{id}")
    public UserModel put(@PathParam("id") int id, @Valid UserModel user) {
        return userService.editUser(id, user);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        userService.deleteUser(id);
    }
}
