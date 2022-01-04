package dropwizard.demo.resource;

import dropwizard.demo.db.UserDAO;
import dropwizard.demo.entities.UserEntity;

import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

@Path("/user")
public class UserResource {

    public UserResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<List<Object[]>> getAllUser() throws SQLException {
        return UserDAO.getAll();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getUser(
            @PathParam("userId") String userId) throws SQLException {
        return UserDAO.findByID(userId);
    }

    @POST
    public void addUser(UserEntity user) throws SQLException {
        UserDAO.addUser(user);
    }

    @PUT
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int updateUser(@PathParam("userId") String userId,
            UserEntity user) throws SQLException {

        if (!validateData(userId, user)) {
            return HttpStatus.BAD_REQUEST_400;
        }

        UserDAO.updateUser(user);
        return HttpStatus.OK_200;
    }

    @DELETE
    @Path("/{user-id}")
    public int deleteUser(@PathParam("user-id") String userId) throws SQLException {
        UserDAO.deleteUser(userId);
        return HttpStatus.OK_200;
    }

    private boolean validateData(String userId, UserEntity user) {
        return userId.equals(String.valueOf(user.getUser_id()));
    }
}