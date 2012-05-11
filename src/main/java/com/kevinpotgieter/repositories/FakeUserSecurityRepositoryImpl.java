package com.kevinpotgieter.repositories;

import com.kevinpotgieter.domain.RESTUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: kevinpotgieter
 * Date: 27/04/2012
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FakeUserSecurityRepositoryImpl implements UserSecurityRepository {

    private RESTUser simulateFetchOfuser() {
        return new RESTUser("username", "password", "api-1234", new ArrayList<GrantedAuthority>());
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        RESTUser exampleUser = simulateFetchOfuser();

        if(username.equalsIgnoreCase(exampleUser.getUsername())){
            return exampleUser;
        }
        return null;
    }

    @Override
    public UserDetails getUserByApiKey(String apiKey) {
        RESTUser exampleUser = simulateFetchOfuser();
        if(apiKey.equals(exampleUser.getApiKey())){
            return exampleUser;
        }
        return null;
    }
}
