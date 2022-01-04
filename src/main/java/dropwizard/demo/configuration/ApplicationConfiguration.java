package dropwizard.demo.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import dropwizard.demo.configuration.DatabaseConfiguration;

import io.dropwizard.Configuration;

public class ApplicationConfiguration extends Configuration {

    @JsonProperty("database")
    private DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDBConfiguration() {
        return databaseConfiguration;
    }
}