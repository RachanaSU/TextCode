package com.rachana.textcode;

import android.util.Base64;
import java.nio.charset.StandardCharsets;

/**
 * TextCodeCipher
 *
 * A custom, key-derived text-transformation scheme (not a standard algorithm
 * such as AES or DES). The user-supplied secret key is turned into a
 * repeating byte keystream, which is combined with the plaintext bytes via
 * XOR. The same key must be supplied on the receiving end to reverse the
 * transformation and recover the original message — so every encoded
 * message is unique to that sender/receiver key pair.
 *
 * This is intentionally simple and readable rather than cryptographically
 * strong: it was built as a learning project to explore how a shared secret
 * can drive a reversible transformation, not as production-grade encryption.
 */
public class TextCodeCipher {

    /**
     * Encrypts plaintext using the given key and returns a Base64 string
     * that is safe to display, copy, or send as plain text.
     */
    public static String encrypt(String plaintext, String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Secret key cannot be empty");
        }
        byte[] textBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[textBytes.length];

        for (int i = 0; i < textBytes.length; i++) {
            byte keyByte = keyBytes[i % keyBytes.length];
            result[i] = (byte) (textBytes[i] ^ keyByte);
        }

        return Base64.encodeToString(result, Base64.NO_WRAP);
    }

    /**
     * Reverses encrypt(): decodes the Base64 payload and XORs it against the
     * same repeating keystream to recover the original plaintext. Returns
     * null if the supplied key does not produce a valid UTF-8 result, which
     * signals the wrong key was used.
     */
    public static String decrypt(String encoded, String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Secret key cannot be empty");
        }
        byte[] cipherBytes;
        try {
            cipherBytes = Base64.decode(encoded, Base64.NO_WRAP);
        } catch (IllegalArgumentException e) {
            return null; // not valid TextCode output
        }

        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[cipherBytes.length];

        for (int i = 0; i < cipherBytes.length; i++) {
            byte keyByte = keyBytes[i % keyBytes.length];
            result[i] = (byte) (cipherBytes[i] ^ keyByte);
        }

        return new String(result, StandardCharsets.UTF_8);
    }
}
