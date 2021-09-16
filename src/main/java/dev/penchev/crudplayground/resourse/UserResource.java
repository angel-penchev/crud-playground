package dev.penchev.crudplayground.resourse;

import dev.penchev.crudplayground.database.UserDAO;
import dev.penchev.crudplayground.user.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {
    UserDAO userDAO;

    public UserResource(UserDAO mainDAO) {
        this.userDAO = mainDAO;
    }

    @GET
    public List<UserModel> get(){
        return userDAO.getAll();
    }

    @POST
    public void post() {

    }
}
