package dropwizard.demo.DAO;

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
import dropwizard.demo.config.ApplicationConfiguration;
import static dropwizard.demo.constants.QueryConstants.*;

public class UserDAO {

    HikariConfig hikariConfig = new HikariConfig();
    QueryRunner runner;
    ResultSetHandler<UserEntity> resultSetHandler = new BeanHandler<>(UserEntity.class);
    ResultSetHandler<List<Object[]>> arrayHandler = new ArrayListHandler();

    public UserDAO(ApplicationConfiguration applicationConfiguration) {
        hikariConfig.setJdbcUrl(applicationConfiguration.getDBConfiguration().getDataBaseURL());
        hikariConfig.setUsername(applicationConfiguration.getDBConfiguration().getDataBaseUsername());
        hikariConfig.setPassword(applicationConfiguration.getDBConfiguration().getDataBasePassword());
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        runner = new QueryRunner(hikariDataSource);
    }

    public UserEntity findByID(String userId) throws SQLException {
        return runner.query(GET_USER_BY_ID + userId, resultSetHandler);
    }

    public List<List<Object[]>> getAll() throws SQLException {
        return runner.execute(GET_ALL_USERS, arrayHandler);
    }

    public void deleteUser(String userId) throws SQLException {
        runner.execute(DELETE_USER_BY_ID + userId, resultSetHandler);
    }

    public void addUser(UserEntity user) throws SQLException {
        runner.insert(QueryConstants.createInsertQuery(user), resultSetHandler);
    }

    public void updateUser(UserEntity user) throws SQLException {
        runner.update(QueryConstants.createUpdateQuery(user));
    }
}