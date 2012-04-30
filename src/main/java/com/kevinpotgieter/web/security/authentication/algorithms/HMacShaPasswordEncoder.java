package com.kevinpotgieter.web.security.authentication.algorithms;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.codec.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: kevinpotgieter
 * Date: 27/04/2012
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class HMacShaPasswordEncoder implements PasswordEncoder {

    private static final int DEFAULT_ENCRYPTION_STRENGTH = 128;
    private static final String ENCODING_FOR_ENCRYPTION = "UTF-8";

    private boolean encodeHashAsBas64 = false;
    private String algorithm;

    /**
     * Initializes the ShaPasswordEncoder for SHA-1 strength
     */
    public HMacShaPasswordEncoder() {
        this(DEFAULT_ENCRYPTION_STRENGTH);
    }

    /**
     * Initialize the ShaPasswordEncoder with a given SHA stength as supported by the JVM
     * EX: <code>HMacShaPasswordEncoder encoder = new HMacShaPasswordEncoder(256);</code> initializes with SHA-256
     *
     * @param strength EX: 1, 256, 384, 512
     */
    public HMacShaPasswordEncoder(int strength) {
        this(strength, false);
    }

    public HMacShaPasswordEncoder(int strength, boolean encodeHashAsBase64) {
        this("HmacSHA" + String.valueOf(strength), encodeHashAsBase64);
    }

    public HMacShaPasswordEncoder(String algorithm, boolean encodeHashAsBase64) {
        this.algorithm = algorithm;
        setEncodeHashAsBas64(encodeHashAsBase64);
        //validity Check
        getMac();

    }

     protected final Mac getMac() throws IllegalArgumentException {
        try {
            return Mac.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }


    public String encodePassword(String rawDataToBeEncrypted, Object salt) {
        byte[] hmacData = null;
        if(rawDataToBeEncrypted != null){
        try {
            SecretKeySpec secretKey = new SecretKeySpec(rawDataToBeEncrypted.getBytes(ENCODING_FOR_ENCRYPTION), this.algorithm);
            Mac mac = getMac();
            mac.init(secretKey);
            hmacData = mac.doFinal(salt.toString().getBytes(ENCODING_FOR_ENCRYPTION));

            if (isEncodeHashAsBas64()) {
                return new String(Base64.encode(hmacData), ENCODING_FOR_ENCRYPTION);
            } else {
                return new String(hmacData, ENCODING_FOR_ENCRYPTION);
            }

        }
        catch(InvalidKeyException ike){
            throw new RuntimeException("Invalid Key while encrypting.", ike);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported Encoding while encrypting.",e);
        }
        }
        return "";

    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        String pass1 = "" + encPass;
        String pass2 = encodePassword(rawPass, salt);

        return equals(pass1, pass2);
    }


    public boolean isEncodeHashAsBas64() {
        return encodeHashAsBas64;
    }

    public void setEncodeHashAsBas64(boolean encodeHashAsBas64) {
        this.encodeHashAsBas64 = encodeHashAsBas64;
    }


    private static boolean equals(String expected, String actual) {
        byte[] expectedBytes = null;
        byte[] actualBytes = null;
        try {
            expectedBytes = expected.getBytes(ENCODING_FOR_ENCRYPTION);
            actualBytes = actual.getBytes(ENCODING_FOR_ENCRYPTION);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported Encoding while encrypting.",e);
        }

        int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
        int actualLength = actualBytes == null ? -1 : actualBytes.length;
        if (expectedLength != actualLength) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expectedLength; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }
}
