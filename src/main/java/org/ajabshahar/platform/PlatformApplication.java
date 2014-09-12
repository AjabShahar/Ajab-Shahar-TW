package org.ajabshahar.platform;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.ajabshahar.platform.resources.HelloWorldResource;

public class PlatformApplication extends Application<PlatformConfiguration> {

  public static void main(String[] args) throws Exception {
    new PlatformApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<PlatformConfiguration> bootstrap) {

  }

  @Override
  public void run(PlatformConfiguration configuration, Environment environment) throws Exception {

    final HelloWorldResource resource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName()
    );

    final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());

    environment.jersey().register(resource);
    environment.healthChecks().register("template", templateHealthCheck);
  }

}
