package dropwizard.demo;

import dropwizard.demo.db.UserDAO;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.Application;

import dropwizard.demo.configuration.ApplicationConfiguration;
import dropwizard.demo.resource.UserResource;

public class MainClass extends Application<ApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainClass().run(args);
    }

    @Override
    public void run(ApplicationConfiguration applicationConfiguration, Environment environment) {

        // You must register the endpoints that you want to expose
        new UserDAO(applicationConfiguration);
        environment.jersey().register(new UserResource());
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    }
}