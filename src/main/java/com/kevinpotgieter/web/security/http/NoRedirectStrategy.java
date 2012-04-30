package com.kevinpotgieter.web.security.http;

import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: kevinpotgieter
 * Date: 27/04/2012
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public class NoRedirectStrategy implements RedirectStrategy{

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        // Forget about redirecting, there is no need!
    }
}
