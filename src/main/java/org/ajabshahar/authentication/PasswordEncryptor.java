package org.ajabshahar.authentication;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

    private static Logger logger = Logger.getLogger(PasswordEncryptor.class);

    public static byte[] getEncryptedPassword(String password,String salt,String algorithm)  {
        try {
            MessageDigest.getInstance(algorithm).update(salt.getBytes());
            return MessageDigest.getInstance(algorithm).digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            logger.error("Could not encrypt the given password using the specified algorithm",e);
            throw new RuntimeException("Could not encrypt the given password using the specified algorithm");
        }
    }
}
