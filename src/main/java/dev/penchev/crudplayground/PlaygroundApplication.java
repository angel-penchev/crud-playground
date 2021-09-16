package dev.penchev.crudplayground;

import dev.penchev.crudplayground.config.PlaygroundConfiguration;
import dev.penchev.crudplayground.database.UserDAO;
import dev.penchev.crudplayground.resourse.UserResource;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class PlaygroundApplication extends Application<PlaygroundConfiguration> {

    public static void main(String[] args) throws Exception {
        new PlaygroundApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<PlaygroundConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<PlaygroundConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(PlaygroundConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(PlaygroundConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final UserResource userResource = new UserResource(userDAO);
        environment.jersey().register(userResource);
    }
}
