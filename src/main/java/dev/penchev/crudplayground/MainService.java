package dev.penchev.crudplayground;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import dev.penchev.crudplayground.resourse.HelloWorld;

public class MainService extends Service<MainServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }
    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {

    }

    @Override
    public void run(MainServiceConfiguration mainServiceConfiguration, Environment environment) throws Exception {
        environment.addResource(new HelloWorld());
    }
}
