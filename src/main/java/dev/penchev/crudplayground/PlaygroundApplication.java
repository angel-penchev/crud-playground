package dev.penchev.crudplayground;

import dev.penchev.crudplayground.config.PlaygroundConfiguration;
import dev.penchev.crudplayground.database.UserDAO;
import dev.penchev.crudplayground.filter.AuthenticationFilter;
import dev.penchev.crudplayground.models.UserModel;
import dev.penchev.crudplayground.resourse.AuthenticationResource;
import dev.penchev.crudplayground.resourse.UserResource;
import dev.penchev.crudplayground.service.AuthenticationService;
import dev.penchev.crudplayground.service.UserService;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
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
    public void run(PlaygroundConfiguration configuration, Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final UserService userService = new UserService(userDAO);
        final UserResource userResource = new UserResource(userService);
        final AuthenticationService authenticationService = new AuthenticationService(userDAO);
        final AuthenticationResource authenticationResource = new AuthenticationResource(authenticationService);
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationService);
        environment.jersey().register(new AuthDynamicFeature(authenticationFilter));
        environment.jersey().register(userResource);
        environment.jersey().register(authenticationResource);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserModel.class));
    }
}
