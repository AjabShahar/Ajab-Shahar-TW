package org.ajabshahar;

import com.ninja_squad.dbsetup.operation.Operation;
import org.ajabshahar.authentication.AjabShaharAuthenticator;
import org.ajabshahar.authentication.PasswordEncryptor;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

public class DataSetup {
    public static byte[] HASHED_PASSWORD = PasswordEncryptor.getEncryptedPassword("password", AjabShaharAuthenticator.SALT,"SHA-256");

    public static final Operation INSERT_ADMIN_USER =
            sequenceOf(
                    insertInto("user")
                            .columns("id", "username", "password")
                            .values(11, "admin", new String(HASHED_PASSWORD))
                            .build());
}
