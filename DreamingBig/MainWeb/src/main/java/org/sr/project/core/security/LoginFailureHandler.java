package org.sr.project.core.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler  extends SimpleUrlAuthenticationFailureHandler {

    //region CONSTRUCTOR
    public LoginFailureHandler (String defaultTargetUrl){
        super.setDefaultFailureUrl(defaultTargetUrl);
    }
    //endregion

    //region PUBLIC METHODS
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException, ServletException{
        super.onAuthenticationFailure(request,response,exception);

        if(exception instanceof BadCredentialsException){
            //save How Many time User has given bad cridentials
        }
    }
    //endregion

}
