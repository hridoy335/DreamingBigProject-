package org.sr.project.core.filters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

    public AuthFilter(CompositeSessionAuthenticationStrategy sessionStrategy, AuthenticationManager authenticationManager){
        super.setSessionAuthenticationStrategy(sessionStrategy);
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        super.doFilter(request,response,chain);
    }


}
