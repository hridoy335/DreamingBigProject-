package org.sr.project.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    //region  CONSTRUCTOR
    public LogoutSuccessHandler(String defaultTargerUrl){
        this.setDefaultTargetUrl(defaultTargerUrl);
    }
    //endregion

    //region PUBLIC METHODS
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException{
        super.onLogoutSuccess(request,response,authentication);
    }
    //endregion
}
