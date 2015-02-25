package org.ajabshahar.authentication;

import com.google.common.base.Optional;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.B64Code;
import org.eclipse.jetty.util.StringUtil;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class TokenAuthProvider {

    private static String TOKEN_HEADER = "token";

    private static final Logger logger = Logger.getLogger(String.valueOf(TokenAuthProvider.class));



    private static class BasicAuthInjectable<T> extends AbstractHttpContextInjectable<T> {
//        private static final String PREFIX = "Basic";
//        private static final String CHALLENGE_FORMAT = PREFIX + " realm=\"%s\"";

        private final Authenticator<BasicCredentials, T> authenticator;
//        private final String realm;
//        private final boolean required;

        private BasicAuthInjectable(Authenticator<BasicCredentials, T> authenticator,
                                    String realm,
                                    boolean required) {
            this.authenticator = authenticator;
//            this.realm = realm;
//            this.required = required;
        }

        @Override
        public T getValue(HttpContext c) {
            final String tokenValue = c.getRequest().getHeaderValue(TOKEN_HEADER);
            try {
                if (tokenValue != null) {
                        final String decoded = B64Code.decode(tokenValue,StringUtil.__ISO_8859_1);
                        final int i = decoded.indexOf(':');
                        if (i > 0) {
                            final String username = decoded.substring(0, i);
                            final String password = decoded.substring(i + 1);
                            final BasicCredentials credentials = new BasicCredentials(username,
                                    password);
                            final Optional<T> result = authenticator.authenticate(credentials);
                            if (result.isPresent()) {
                                return result.get();
                            }
                        }
                }
            } catch (IllegalArgumentException e) {
                logger.debug("Error decoding credentials", e);
            } catch (AuthenticationException e) {
                logger.warn("Error authenticating credentials", e);
                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
            }
            return null;
        }
    }


}
