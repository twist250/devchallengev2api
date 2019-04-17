package com.devchallengev2.devapi.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class SystemUtils {
    public static String generateKey(int len, boolean readable, boolean hash) {
        String key = "";
        if (hash) {
            key = sha1(uniqid(new Random().nextInt() + "", true)).substring(0, len);
        } else if (readable) {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            for (int i = 0; i < len; ++i) {
                key += chars.substring(1,(new Random().nextInt((36 - 1) + 1) + 1));
            }
        } else {
            for (int i = 0; i < len; i++) {
                key += (char) ((Math.random() * ((126 - 33) + 1)) + 33);

            }
        }
        return key + generateRandomString(150);
    }

    public static String generateRandomString(int len) {
        len = 10;
        char[] characters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
                'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        int length = characters.length;
        String randomString = "";
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            randomString += characters[random.nextInt(length - 1)];
        }
        return randomString;
    }

    //this method creates a unique id from time
    public static String uniqid(String prefix, boolean more_entropy) {
        long time = System.currentTimeMillis();
        String uniqid = "";
        if (!more_entropy) {

            uniqid = String.format("%s%08x%05x", prefix, time / 1000, time);
        } else {
            SecureRandom sec = new SecureRandom();
            byte[] sbuf = sec.generateSeed(8);
            ByteBuffer bb = ByteBuffer.wrap(sbuf);
            uniqid = String.format("%s%08x%05x", prefix, time / 1000, time);
            uniqid += "." + String.format("%.8s", "" + bb.getLong() * -1);
        }


        return uniqid;
    }

    //this method is for hashing using SHA-1
    public static String sha1(String str) {
        String hashStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            hashStr = hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashStr;
    }
}
