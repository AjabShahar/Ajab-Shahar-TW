package org.ajabshahar.platform;

import com.bazaarvoice.dropwizard.caching.CacheControlConfiguration;
import com.bazaarvoice.dropwizard.caching.CachingBundleConfiguration;
import com.bazaarvoice.dropwizard.caching.CachingConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PlatformConfiguration extends Configuration implements CachingBundleConfiguration {
    private CachingConfiguration cache = new CachingConfiguration();
    public CacheControlConfiguration cacheControl = new CacheControlConfiguration();

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    private String sessionTimeout;

    @NotEmpty
    private String salt;

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    @JsonProperty
    public String getSessionTimeout() {
        return sessionTimeout;
    }

    @JsonProperty
    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    @Override
    public CachingConfiguration getCache() {
        return cache;
    }

    @Override
    public CacheControlConfiguration getCacheControl() {
        return cacheControl;
    }

    @JsonProperty
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
