package org.ajabshahar.platform;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.ajabshahar.platform.daos.SplashScreenOptionsDAO;
import org.ajabshahar.platform.models.SplashScreenOptions;
import org.ajabshahar.platform.resources.HelloWorldResource;
import org.ajabshahar.platform.resources.SplashScreenOptionsResource;
import org.ajabshahar.platform.resources.WordDetailsResource;
import org.ajabshahar.platform.daos.WordDetailsDAO;
import org.ajabshahar.platform.models.WordDetails;


public class PlatformApplication extends Application<PlatformConfiguration> {

  private MigrationsBundle<PlatformConfiguration> migrationsBundle = new MigrationsBundle<PlatformConfiguration>() {
    @Override
    public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
      return configuration.getDataSourceFactory();
    }
  };

  private final HibernateBundle<PlatformConfiguration> hibernate = new HibernateBundle<PlatformConfiguration>(SplashScreenOptions.class) {
     @Override
     public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
       return configuration.getDataSourceFactory();
     }
  };

  private final HibernateBundle<PlatformConfiguration> wordHibernate = new HibernateBundle<PlatformConfiguration>(WordDetails.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
  };

  @Override
  public void initialize(Bootstrap<PlatformConfiguration> bootstrap) {
    bootstrap.addBundle(hibernate);
    bootstrap.addBundle(wordHibernate);
    bootstrap.addBundle(migrationsBundle);

    bootstrap.addBundle(new AssetsBundle("/assets/app", "/","index.html"));
  }

  @Override
  public void run(PlatformConfiguration configuration, Environment environment) throws Exception {

    final HelloWorldResource resource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName()
    );
      final SplashScreenOptionsDAO dao = new SplashScreenOptionsDAO(hibernate.getSessionFactory());
      final WordDetailsDAO detailsDAO = new WordDetailsDAO(wordHibernate.getSessionFactory());

    final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());

    environment.jersey().setUrlPattern("/api/*");
    environment.jersey().register(resource);
    environment.jersey().register(new SplashScreenOptionsResource(dao));
    environment.jersey().register(new WordDetailsResource(detailsDAO));
    environment.healthChecks().register("template", templateHealthCheck);
  }

  public static void main(String[] args) throws Exception {
    new PlatformApplication().run(args);
  }

}
