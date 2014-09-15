package org.ajabshahar.platform;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.ajabshahar.platform.models.Person;
import org.ajabshahar.platform.resources.HelloWorldResource;

public class PlatformApplication extends Application<PlatformConfiguration> {

  private MigrationsBundle<PlatformConfiguration> migrationsBundle = new MigrationsBundle<PlatformConfiguration>() {
    @Override
    public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
      return configuration.getDataSourceFactory();
    }
  };

  private final HibernateBundle<PlatformConfiguration> hibernate = new HibernateBundle<PlatformConfiguration>(Person.class) {
    @Override
    public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
      return configuration.getDataSourceFactory();
    }
  };

  @Override
  public void initialize(Bootstrap<PlatformConfiguration> bootstrap) {
    bootstrap.addBundle(hibernate);
    bootstrap.addBundle(migrationsBundle);
    bootstrap.addBundle(new AssetsBundle("/assets/app", "/","index.html"));
  }

  @Override
  public void run(PlatformConfiguration configuration, Environment environment) throws Exception {

    final HelloWorldResource resource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName()
    );

    final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());

    environment.jersey().setUrlPattern("/api/*");
    environment.jersey().register(resource);
    environment.healthChecks().register("template", templateHealthCheck);
  }

  public static void main(String[] args) throws Exception {
    new PlatformApplication().run(args);
  }

}
