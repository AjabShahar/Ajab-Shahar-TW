package org.ajabshahar.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkRepresentation {
    private String rel;
    private String url;

    public LinkRepresentation() {
    }

    public LinkRepresentation(String rel, String url) {
        this.rel = rel;
        this.url = url;
    }

    @JsonProperty("rel")
    public String getRel() {
        return rel;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }
}
