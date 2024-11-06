package com.kyeonjuk.auth.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 암호화, 복호화 해주는 util 클래스
public class SHA256 {

    /*
        암호화
     */
    public static String encrypt(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Failed to encrypt password");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private SHA256() {

    }
}
