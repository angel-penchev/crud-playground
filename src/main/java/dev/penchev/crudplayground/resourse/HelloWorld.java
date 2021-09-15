package dev.penchev.crudplayground.resourse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HelloWorld {
    @GET
    public String getRoot() {
        return "Hello world!";
    }
}
