package com.kevinpotgieter.web.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: kevinpotgieter
 * Date: 27/04/2012
 * Time: 10:23
 * To change this template use File | Settings | File Templates.
 */
public class RESTAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public RESTAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public RESTAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
