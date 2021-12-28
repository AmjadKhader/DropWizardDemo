package dropwizard.demo.controller;

import dropwizard.demo.DAO.UserDAO;
import dropwizard.demo.entities.UserEntity;

import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

@Path("/user")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<List<Object[]>> getAllUser() throws SQLException {
        return userDAO.getAll();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getUser(
            @PathParam("userId")
                    String userId) throws SQLException {
        return userDAO.findByID(userId);
    }

    @POST
    public void addUser(UserEntity user) throws SQLException {
        userDAO.addUser(user);
    }

    @PUT
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int updateUser(
            @PathParam("userId") String userId,
            UserEntity user) throws SQLException {

        if (!validateData(userId, user)) {
            return HttpStatus.BAD_REQUEST_400;
        }
        userDAO.updateUser(user);
        return HttpStatus.OK_200;
    }


    @DELETE
    @Path("/{user-id}")
    public int deleteUser(
            @PathParam("user-id")
                    String userId) throws SQLException {
        userDAO.deleteUser(userId);
        return HttpStatus.OK_200;
    }


    private boolean validateData(String userId, UserEntity user) {
        return userId.equals(String.valueOf(user.getUser_id()));
    }
}