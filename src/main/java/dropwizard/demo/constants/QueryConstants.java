package dropwizard.demo.constants;

import dropwizard.demo.entities.UserEntity;

public class QueryConstants {

    private static final String TABLE_NAME = "User";
    private static final String WHERE_USER_ID = " WHERE user_id=";

    public static final String GET_ALL_USERS = "SELECT * FROM " + TABLE_NAME + ";";
    public static final String GET_USER_BY_ID = "SELECT * FROM " + TABLE_NAME + WHERE_USER_ID;
    public static final String DELETE_USER_BY_ID = "DELETE FROM " + TABLE_NAME + WHERE_USER_ID;

    public static String createInsertQuery(UserEntity user) {
        return "INSERT INTO User (user_id, User_name) VALUES (" + user.getUser_id() + ", '" + user.getUser_name() + "');";
    }

    public static String createUpdateQuery(UserEntity user) {
        return "UPDATE User SET User_name='" + user.getUser_name() + "' WHERE user_id=" + user.getUser_id();
    }
}