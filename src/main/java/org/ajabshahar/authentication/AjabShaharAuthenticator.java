package org.ajabshahar.authentication;


import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.ajabshahar.core.Users;
import org.ajabshahar.platform.models.User;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AjabShaharAuthenticator implements Authenticator<BasicCredentials, Principle> {

    public static final String SALT = "A very long salt text.";
    private Users users;
    private Logger logger = Logger.getLogger(this.getClass());

    public AjabShaharAuthenticator(Users users) {
        this.users = users;
    }
    private String getHashedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(AjabShaharAuthenticator.SALT.getBytes());
        return new String(digest.digest(password.getBytes()));
    }

    @Override
    public Optional<Principle> authenticate(BasicCredentials credentials) throws AuthenticationException {
        User user = users.getUser(credentials.getUsername());
        try {
            if (user != null && passwordsMatch(credentials, user)){
                Principle principle = new Principle(user.getUserName());
                return Optional.fromNullable(principle);
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("Encryption algorithm not available",e);
            throw new RuntimeException("Error :Could not authenticate the user");
        }
        return Optional.absent();
    }

    private boolean passwordsMatch(BasicCredentials credentials, User user) throws NoSuchAlgorithmException {
        return user.getPassword().equals(getHashedPassword(credentials.getPassword()));
    }
}
