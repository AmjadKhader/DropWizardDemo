package dropwizard.demo;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.Application;

import dropwizard.demo.config.ApplicationConfiguration;
import dropwizard.demo.controller.UserController;
import dropwizard.demo.DAO.UserDAO;

public class MainClass extends Application<ApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainClass().run(args);
    }

    @Override
    public void run(ApplicationConfiguration applicationConfiguration, Environment environment) {

        // You must register the endpoints that you want to expose
        UserDAO userDAO = new UserDAO(applicationConfiguration);
        environment.jersey().register(userDAO);
        environment.jersey().register(new UserController(userDAO));
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    }
}