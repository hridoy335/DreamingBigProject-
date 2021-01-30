package org.sr.project.core.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.ELRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    //region PRIVATE FIELDS
    private static final RequestMatcher REQUEST_MATCHER =  new ELRequestMatcher("hasHeader('X-Requested-With','XMLHttpRequest')");
    //endregion

    //region CONSTRUCTORS
    public AuthenticationEntryPoint(String loginUrl){
        super(loginUrl);
    }
    //endregion

    //region PUBLIC METHODS
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
    throws IOException, ServletException {
        if(REQUEST_MATCHER.matches(request)){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"SESSION_TIMED_OUT");
        }else{
            request.setAttribute("targetUrl",request.getRequestURL());
            super.commence(request,response,authenticationException);
        }
    }
    //endregion

}
