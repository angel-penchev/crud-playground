package dev.penchev.crudplayground.core;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtilities {
    public static boolean checkPassword(final String passwordToCheck, final String referencePasswordHash) {
        return BCrypt.checkpw(passwordToCheck, referencePasswordHash);
    }

    public static String generateFromPassword(final String password) {
        final String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
