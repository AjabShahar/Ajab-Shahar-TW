package org.ajabshahar.platform;

import com.bazaarvoice.dropwizard.caching.CachingBundle;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.sessions.HttpSessionProvider;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.ajabshahar.api.*;
import org.ajabshahar.authentication.PasswordAuthenticator;
import org.ajabshahar.authentication.Principle;
import org.ajabshahar.authentication.SessionAuthenticatorFilter;
import org.ajabshahar.core.*;
import org.ajabshahar.platform.controller.LoginController;
import org.ajabshahar.platform.controller.LogoutController;
import org.ajabshahar.platform.daos.*;
import org.ajabshahar.platform.models.*;
import org.ajabshahar.platform.resources.*;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.injectors.SetterInjection;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class PlatformApplication extends Application<PlatformConfiguration> {

    private MigrationsBundle<PlatformConfiguration> migrationsBundle = new MigrationsBundle<PlatformConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(PlatformConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final HibernateBundle<PlatformConfiguration> hibernate = new HibernateBundle<PlatformConfiguration>(SplashScreenOptions.class, Word.class,
            Couplet.class, Song.class, PersonDetails.class, Category.class, Title.class, SongText.class, SongTextContent.class, OpeningCouplet.class,
            WordIntroduction.class, Reflection.class, ReflectionTranscript.class, Genre.class, User.class, Gathering.class) {
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
        bootstrap.addBundle(new AssetsBundle("/assets/app/user/css", "/user-css", null, "user-css"));
        bootstrap.addBundle(new AssetsBundle("/assets/app/user/js", "/user-js", null, "user-js"));
        bootstrap.addBundle(new AssetsBundle("/assets/app/user/img", "/user-img", null, "user-img"));

        bootstrap.addBundle(new AssetsBundle("/assets/app/admin/css", "/admin-css", null, "admin-css"));
        bootstrap.addBundle(new AssetsBundle("/assets/app/admin/js", "/admin-js", null, "admin-js"));
        bootstrap.addBundle(new AssetsBundle("/assets/app/admin/img", "/admin-img", null, "admin-img"));

        bootstrap.addBundle(new AssetsBundle("/assets/app/admin/partials", "/admin", null, "admin"));

        bootstrap.addBundle(new AssetsBundle("/assets/app/common", "/common", null, "common"));

        bootstrap.addBundle(new AssetsBundle("/assets/app/user/partials/common", "/pendingPages", null, "pendingPages"));

        bootstrap.addBundle(new AssetsBundle("/assets/app/user/partials", "/", "index.html"));
    }

    @Override
    public void run(PlatformConfiguration configuration, Environment environment) throws Exception {
        final int _30_MINUTES = 30 * 60;

        DefaultPicoContainer picoContainer = addToPicoContainer();

        TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck("");

        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(picoContainer.getComponent(SplashScreenOptionsResource.class));
        environment.jersey().register(picoContainer.getComponent(WordResource.class));
        environment.jersey().register(picoContainer.getComponent(CoupletResource.class));
        environment.jersey().register(picoContainer.getComponent(SongResource.class));
        environment.jersey().register(picoContainer.getComponent(PersonResource.class));
        environment.jersey().register(picoContainer.getComponent(CategoryResource.class));
        environment.jersey().register(picoContainer.getComponent(TitleResource.class));
        environment.jersey().register(picoContainer.getComponent(ReflectionResource.class));
        environment.jersey().register(picoContainer.getComponent(GenreResource.class));
        environment.jersey().register(picoContainer.getComponent(GatheringResource.class));
        environment.jersey().register(picoContainer.getComponent(LoginController.class));
        environment.jersey().register(picoContainer.getComponent(LogoutController.class));
        environment.jersey().register(HttpSessionProvider.class);
        SessionManager sessionManager = new HashSessionManager();
        String sessionTimeout = configuration.getSessionTimeout();
        int timeout = sessionTimeout != null ? Integer.parseInt(sessionTimeout) : _30_MINUTES;
        sessionManager.setMaxInactiveInterval(timeout);
        environment.servlets().setSessionHandler(new SessionHandler(sessionManager));
        environment.servlets().addFilter("SessionAuthFilter", new SessionAuthenticatorFilter()).addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        PasswordAuthenticator authenticator = picoContainer.getComponent(PasswordAuthenticator.class);
        environment.jersey().register(new BasicAuthProvider<Principle>(authenticator, "Ajab-shahar"));

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
        picoContainer.addComponent(SongTextDAO.class);
        picoContainer.addComponent(ReflectionDAO.class);
        picoContainer.addComponent(GenreDAO.class);
        picoContainer.addComponent(UserDAO.class);
        picoContainer.addComponent(GatheringDAO.class);

        picoContainer.addComponent(Songs.class);
        picoContainer.addComponent(Lyrics.class);
        picoContainer.addComponent(Couplets.class);
        picoContainer.addComponent(People.class);
        picoContainer.addComponent(Words.class);
        picoContainer.addComponent(Reflections.class);
        picoContainer.addComponent(SongsRepresentationFactory.class);
        picoContainer.addComponent(PersonRepresentationFactory.class);
        picoContainer.addComponent(CoupletsRepresentationFactory.class);

        WordRepresentationFactory wordRepresentationFactory = new WordRepresentationFactory();
        ReflectionRepresentationFactory reflectionRepresentationFactory = new ReflectionRepresentationFactory();
        reflectionRepresentationFactory.injectWordRepresentationFactory(wordRepresentationFactory);
        wordRepresentationFactory.injectReflectionRepresentationFactory(reflectionRepresentationFactory);
        reflectionRepresentationFactory.injectReflectionDao(picoContainer.getComponent(ReflectionDAO.class));

        picoContainer.addComponent(reflectionRepresentationFactory);
        picoContainer.addComponent(wordRepresentationFactory);
        picoContainer.addComponent(Users.class);
        picoContainer.addComponent(SplashScreenOptionsResource.class);
        picoContainer.addComponent(WordResource.class);
        picoContainer.addComponent(CoupletResource.class);
        picoContainer.addComponent(SongResource.class);
        picoContainer.addComponent(PersonResource.class);
        picoContainer.addComponent(CategoryResource.class);
        picoContainer.addComponent(TitleResource.class);
        picoContainer.addComponent(ReflectionResource.class);
        picoContainer.addComponent(GenreResource.class);
        picoContainer.addComponent(GatheringResource.class);
        picoContainer.addComponent(LoginController.class);
        picoContainer.addComponent(LogoutController.class);
        picoContainer.addComponent(PasswordAuthenticator.class);
        picoContainer.addComponent(SessionAuthenticatorFilter.class);

        return picoContainer;
    }

    public static void main(String[] args) throws Exception {
        new PlatformApplication().run(args);
    }

}
