package com.n00b5.simplist;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/9/17
 */
public abstract class Authentication {

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean validatePassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }
}
