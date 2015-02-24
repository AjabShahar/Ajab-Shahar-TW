package org.ajabshahar.authentication;


import com.google.common.base.Optional;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.ajabshahar.core.Users;
import org.ajabshahar.platform.models.User;
import org.apache.log4j.Logger;

public class AjabShaharAuthenticator implements Authenticator<BasicCredentials, Principle> {

    public static final String SALT = "A very long salt text.";
    private Users users;
    private Logger logger = Logger.getLogger(this.getClass());

    public AjabShaharAuthenticator(Users users) {
        this.users = users;
    }


    @Override
    public Optional<Principle> authenticate(BasicCredentials credentials) {
        User user = users.getUser(credentials.getUsername());
        if (user != null && passwordsMatch(credentials, user)) {
            Principle principle = new Principle(user.getUsername());
            return Optional.fromNullable(principle);
        }
        return Optional.absent();
    }

    private boolean passwordsMatch(BasicCredentials credentials, User user)  {
        return user.getPassword().equals(getHashedPassword(credentials.getPassword()));
    }

    private String getHashedPassword(String password) {
        return new String(PasswordEncryptor.getEncryptedPassword(password, SALT, "SHA-256"));
    }
}
