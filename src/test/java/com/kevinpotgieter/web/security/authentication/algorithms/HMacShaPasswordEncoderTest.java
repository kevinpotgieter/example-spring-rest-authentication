package com.kevinpotgieter.web.security.authentication.algorithms;

import org.junit.Test;
import org.springframework.security.crypto.codec.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: kevinpotgieter
 * Date: 27/04/2012
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 */
public class HMacShaPasswordEncoderTest {

    @Test
    public void test() throws GeneralSecurityException, UnsupportedEncodingException {
        String secretKey = "password";
        String salt = "0123456789";

        String expectedHmacSHA256Signature = generateHmacSHA256Signature(salt, secretKey,true);
        System.out.println(expectedHmacSHA256Signature);
        System.out.println(URLEncoder.encode(expectedHmacSHA256Signature, "UTF-8"));
    }

    @Test
    public void test_password_encyrpted_string_is_correct_with_not_encoded_as_base_64() throws UnsupportedEncodingException, GeneralSecurityException {
        String secretKey = "secretKey";
        String salt = "0123456789";

        boolean encodeAsBase64 = false;

        String expectedHmacSHA256Signature = generateHmacSHA256Signature(salt, secretKey,encodeAsBase64);
        HMacShaPasswordEncoder hMacShaPasswordEncoder = new HMacShaPasswordEncoder(256, encodeAsBase64);
        String actualHmacSHA256Signature =  hMacShaPasswordEncoder.encodePassword(secretKey,salt);
        assertEquals(expectedHmacSHA256Signature, actualHmacSHA256Signature);
    }

    @Test
    public void test_password_encyrpted_string_is_correct_encoded_as_base_64() throws UnsupportedEncodingException, GeneralSecurityException {
        String secretKey = "secretKey";
        String salt = "0123456789";

        boolean encodeAsBase64 = true;

        String expectedHmacSHA256Signature = generateHmacSHA256Signature(salt, secretKey,encodeAsBase64);

        HMacShaPasswordEncoder hMacShaPasswordEncoder = new HMacShaPasswordEncoder(256, encodeAsBase64);
        String actualHmacSHA256Signature =  hMacShaPasswordEncoder.encodePassword(secretKey,salt);
        assertEquals(expectedHmacSHA256Signature, actualHmacSHA256Signature);
    }

    @Test
    public void test_password_is_valid_should_succeed() throws UnsupportedEncodingException, GeneralSecurityException {
        String secretKey = "secretKey";
        String salt = "0123456789";

        boolean encodeAsBase64 = true;

        String expectedHmacSHA256Signature = generateHmacSHA256Signature(salt, secretKey,encodeAsBase64);

        HMacShaPasswordEncoder hMacShaPasswordEncoder = new HMacShaPasswordEncoder(256, encodeAsBase64);
        assertTrue("PasswordEncoder isPasswordValid failed to successfully compare cryptographic hashes", hMacShaPasswordEncoder.isPasswordValid(expectedHmacSHA256Signature, secretKey, salt));

    }


    private static String generateHmacSHA256Signature(String data, String key, boolean encodeBase64) throws GeneralSecurityException {
        byte[] hmacData = null;

        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            hmacData = mac.doFinal(data.getBytes("UTF-8"));

            if(encodeBase64){
                return new String(Base64.encode(hmacData));
            }
            else{
                return new String(hmacData, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }}
