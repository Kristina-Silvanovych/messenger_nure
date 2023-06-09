package ua.com.alevel.messenger_nure.util;


import org.apache.commons.codec.digest.DigestUtils;

public final class Security {
    private Security(){
        throw new IllegalStateException("Utility class.");
    }
    public static String md5Apache(String st) {
        return DigestUtils.md5Hex(st);
    }
}
