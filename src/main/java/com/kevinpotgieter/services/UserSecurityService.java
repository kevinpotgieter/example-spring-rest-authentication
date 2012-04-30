package com.kevinpotgieter.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by IntelliJ IDEA.
 * User: kevinpotgieter
 * Date: 27/04/2012
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 */
public interface UserSecurityService extends UserDetailsService {

    UserDetails getUserByApiKey(String apiKey);
}
