package org.ajabshahar;

import com.ninja_squad.dbsetup.operation.Operation;
import org.ajabshahar.authentication.PasswordAuthenticator;
import org.ajabshahar.authentication.PasswordEncryptor;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

public class DataSetup {
    public static byte[] HASHED_PASSWORD = PasswordEncryptor.getEncryptedPassword("password", PasswordAuthenticator.SALT,PasswordAuthenticator.ALGORITHM);

    public static final Operation DELETE_ALL =
            deleteAllFrom("USERS");

    public static final Operation INSERT_ADMIN_USER =
            sequenceOf(
                    insertInto("USERS")
                            .columns("id", "username", "password")
                            .values(11, "admin", new String(HASHED_PASSWORD))
                            .build());
}
