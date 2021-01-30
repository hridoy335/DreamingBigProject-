package org.sr.project.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebAccessDeniedHandler implements AccessDeniedHandler {

    //region PRIVATE FIELDS
    private final static String LOGOUT_URL = "logout";
    private final static String AUTH_URL = "auth";
    private final static String defaultTargetUrl = "/home";

    private String accessDeniedUrl;
    private RedirectStrategy redirectStrategy =  new DefaultRedirectStrategy();



    //TODO: User Login Service
    //@Autowired
    //private IuserLoginService userLoginService;
    //endregion

    //region PUBLIC METHODS
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        String url = httpServletRequest.getServletPath();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            if(!httpServletResponse.isCommitted()){
                if(e instanceof MissingCsrfTokenException || e instanceof InvalidCsrfTokenException){
                    if(url.replaceFirst("/","").equals(AUTH_URL)){
                        this.redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,defaultTargetUrl);
                    }else if(url.replaceFirst("/","").equals(LOGOUT_URL)){
                        this.redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,url);
                    }else{
                        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }else{
                    //TODO: User LogIN DTO & userLoginService
                    //UserLoginDTO userLoginDTO = (UserLoginDTO) authentication.getPrincipal();
                    //userLoginService.accessDenied(userLoginDTO.getUserName(),url);
                    this.redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,accessDeniedUrl);
                }
            }else{
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }else{
            if(url.replaceFirst("/","").equals(LOGOUT_URL)){
                this.redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,url);
            }else{
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }

    }
    //endregion

}
