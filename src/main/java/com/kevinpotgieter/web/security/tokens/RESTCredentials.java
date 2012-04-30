package com.kevinpotgieter.web.security.tokens;

/**
 * Created by IntelliJ IDEA.
 * User: kevinpotgieter
 * Date: 27/04/2012
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
public final class RESTCredentials {

    private String requestSalt;
    private String secureHash;

    private RESTCredentials() {
    }

    public RESTCredentials(String requestSalt, String secureHash) {
        this.requestSalt = requestSalt;
        this.secureHash = secureHash;
    }

    public String getRequestSalt() {
        return requestSalt;
    }

    public String getSecureHash() {
        return secureHash;
    }
}
