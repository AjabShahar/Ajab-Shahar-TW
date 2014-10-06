package org.ajabshahar.platform;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.ajabshahar.platform.daos.SplashScreenDAO;
import org.ajabshahar.platform.models.SplashScreen;
import org.ajabshahar.platform.resources.HelloWorldResource;
import org.ajabshahar.platform.resources.SplashScreenResource;

public class PlatformApplication extends Application<PlatformConfiguration> {

  private MigrationsBundle<PlatformConfiguration> migrationsBundle = new MigrationsBundle<PlatformConfiguration>() {
    @Override
    public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
      return configuration.getDataSourceFactory();
    }
  };

  // private final HibernateBundle<PlatformConfiguration> hibernate = new HibernateBundle<PlatformConfiguration>(SplashScreen.class) {
  //   @Override
  //   public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
  //     return configuration.getDataSourceFactory();
  //   }
  // };

  @Override
  public void initialize(Bootstrap<PlatformConfiguration> bootstrap) {
    // bootstrap.addBundle(hibernate);
    bootstrap.addBundle(migrationsBundle);

    bootstrap.addBundle(new AssetsBundle("/assets/app", "/","*.html"));
  }

  @Override
  public void run(PlatformConfiguration configuration, Environment environment) throws Exception {

    final HelloWorldResource resource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName()
    );
      // final SplashScreenDAO dao = new SplashScreenDAO (hibernate.getSessionFactory());

    final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());

    environment.jersey().setUrlPattern("/api/*");
    environment.jersey().register(resource);
    // environment.jersey().register(new SplashScreenResource(dao));
    environment.healthChecks().register("template", templateHealthCheck);
  }

  public static void main(String[] args) throws Exception {
    new PlatformApplication().run(args);
  }

}
