package org.ajabshahar.authentication;


import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.ajabshahar.core.Users;
import org.ajabshahar.platform.PlatformConfiguration;
import org.ajabshahar.platform.models.User;
import org.apache.log4j.Logger;

public class PasswordAuthenticator implements Authenticator<BasicCredentials, Principle> {

    public static String SALT;
    public static final String ALGORITHM = "SHA-512";
    private Users users;
    private Logger logger = Logger.getLogger(this.getClass());

    public PasswordAuthenticator(Users users, String salt) {
        this.users = users;
        SALT = salt;
    }


    @Override
    public Optional<Principle> authenticate(BasicCredentials credentials) throws AuthenticationException {
        User user = users.getUser(credentials.getUsername());
        if (user != null && passwordsMatch(credentials, user)) {
            Principle principle = new Principle(user.getUsername(), user.getRole());
            return Optional.fromNullable(principle);
        }
        return Optional.absent();
    }

    private boolean passwordsMatch(BasicCredentials credentials, User user) {
        return user.getPassword().equals(getHashedPassword(credentials.getPassword()));
    }

    private String getHashedPassword(String password) {
        return new String(PasswordEncryptor.getEncryptedPassword(password, SALT, ALGORITHM));
    }
}
