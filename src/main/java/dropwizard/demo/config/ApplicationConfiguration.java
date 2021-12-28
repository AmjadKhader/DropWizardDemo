package dropwizard.demo.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import dropwizard.demo.resources.DatabaseResource;

import io.dropwizard.Configuration;

public class ApplicationConfiguration extends Configuration {

    @JsonProperty("database")
    private DatabaseResource databaseResource;

    public DatabaseResource getDBConfiguration() {
        return databaseResource;
    }
}