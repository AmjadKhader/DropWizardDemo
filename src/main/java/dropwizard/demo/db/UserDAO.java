package dropwizard.demo.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.SQLException;
import java.util.List;

import dropwizard.demo.entities.UserEntity;
import dropwizard.demo.constants.QueryConstants;
import dropwizard.demo.configuration.ApplicationConfiguration;

import static dropwizard.demo.constants.QueryConstants.*;

public class UserDAO {

    HikariConfig hikariConfig = new HikariConfig();
    static QueryRunner runner;
    static ResultSetHandler<UserEntity> resultSetHandler = new BeanHandler<>(UserEntity.class);
    static ResultSetHandler<List<Object[]>> arrayHandler = new ArrayListHandler();

    public UserDAO(ApplicationConfiguration applicationConfiguration) {
        hikariConfig.setJdbcUrl(applicationConfiguration.getDBConfiguration().getDataBaseURL());
        hikariConfig.setUsername(applicationConfiguration.getDBConfiguration().getDataBaseUsername());
        hikariConfig.setPassword(applicationConfiguration.getDBConfiguration().getDataBasePassword());
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        runner = new QueryRunner(hikariDataSource);
    }

    public static UserEntity findByID(String userId) throws SQLException {
        return runner.query(GET_USER_BY_ID + userId, resultSetHandler);
    }

    public static List<List<Object[]>> getAll() throws SQLException {
        return runner.execute(GET_ALL_USERS, arrayHandler);
    }

    public static void deleteUser(String userId) throws SQLException {
        runner.execute(DELETE_USER_BY_ID + userId, resultSetHandler);
    }

    public static void addUser(UserEntity user) throws SQLException {
        runner.insert(QueryConstants.createInsertQuery(user), resultSetHandler);
    }

    public static void updateUser(UserEntity user) throws SQLException {
        runner.update(QueryConstants.createUpdateQuery(user));
    }
}