package com.kevinpotgieter.repositories;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by IntelliJ IDEA.
 * User: kevinpotgieter
 * Date: 27/04/2012
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public interface UserSecurityRepository {

    UserDetails getUserByUsername(String username);
    UserDetails getUserByApiKey(String apiKey);
}
