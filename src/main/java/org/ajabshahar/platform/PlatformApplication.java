package org.ajabshahar.platform;

import com.bazaarvoice.dropwizard.caching.CachingBundle;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.ajabshahar.api.CoupletsRepresentationFactory;
import org.ajabshahar.api.PersonRepresentationFactory;
import org.ajabshahar.api.SongsRepresentationFactory;
import org.ajabshahar.core.Couplets;
import org.ajabshahar.core.People;
import org.ajabshahar.core.Songs;
import org.ajabshahar.platform.daos.*;
import org.ajabshahar.platform.models.*;
import org.ajabshahar.platform.resources.*;
import org.picocontainer.DefaultPicoContainer;

public class PlatformApplication extends Application<PlatformConfiguration> {

    private MigrationsBundle<PlatformConfiguration> migrationsBundle = new MigrationsBundle<PlatformConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final HibernateBundle<PlatformConfiguration> hibernate = new HibernateBundle<PlatformConfiguration>(SplashScreenOptions.class, Word.class,
            Couplet.class, Song.class, PersonDetails.class, Category.class, Title.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private CachingBundle cachingBundle = new CachingBundle();

    @Override
    public void initialize(Bootstrap<PlatformConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(migrationsBundle);
        bootstrap.addBundle(cachingBundle);

        bootstrap.addBundle(new AssetsBundle("/assets/app", "/", "index.html"));
    }

    @Override
    public void run(PlatformConfiguration configuration, Environment environment) throws Exception {
        DefaultPicoContainer picoContainer = addToPicoContainer();

        TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck("");

        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(picoContainer.getComponent(SplashScreenOptionsResource.class));
        environment.jersey().register(picoContainer.getComponent(WordResource.class));
        environment.jersey().register(picoContainer.getComponent(CoupletResource.class));
        environment.jersey().register(picoContainer.getComponent(EditorsChoiceResource.class));
        environment.jersey().register(picoContainer.getComponent(SongResource.class));
        environment.jersey().register(picoContainer.getComponent(PersonResource.class));
        environment.jersey().register(picoContainer.getComponent(CategoryResource.class));
        environment.jersey().register(picoContainer.getComponent(TitleResource.class));
        environment.healthChecks().register("template", templateHealthCheck);
    }

    private DefaultPicoContainer addToPicoContainer() {
        DefaultPicoContainer picoContainer = new DefaultPicoContainer();

        picoContainer.addComponent(hibernate.getSessionFactory());
        picoContainer.addComponent(SplashScreenOptionsDAO.class);
        picoContainer.addComponent(WordDAO.class);
        picoContainer.addComponent(CoupletDAO.class);
        picoContainer.addComponent(SongDAO.class);
        picoContainer.addComponent(PersonDAO.class);
        picoContainer.addComponent(CategoryDAO.class);
        picoContainer.addComponent(TitleDAO.class);

        picoContainer.addComponent(Songs.class);
        picoContainer.addComponent(Couplets.class);
        picoContainer.addComponent(People.class);
        picoContainer.addComponent(SongsRepresentationFactory.class);
        picoContainer.addComponent(PersonRepresentationFactory.class);
        picoContainer.addComponent(CoupletsRepresentationFactory.class);

        picoContainer.addComponent(SplashScreenOptionsResource.class);
        picoContainer.addComponent(WordResource.class);
        picoContainer.addComponent(CoupletResource.class);
        picoContainer.addComponent(EditorsChoiceResource.class);
        picoContainer.addComponent(SongResource.class);
        picoContainer.addComponent(PersonResource.class);
        picoContainer.addComponent(CategoryResource.class);
        picoContainer.addComponent(TitleResource.class);

        return picoContainer;
    }

    public static void main(String[] args) throws Exception {
        new PlatformApplication().run(args);
    }

}
