package org.sr.project.core.security;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxTimeoutRedirectFilter extends GenericFilterBean {

    //region PRIVATE FIELDS
    private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
    //endregion


    //region PUBLIC METHODS
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(authenticationTrustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())){
            String ajaxHolder = ((HttpServletRequest) servletRequest).getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(ajaxHolder)){
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendError(901);
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    //endregion

}
