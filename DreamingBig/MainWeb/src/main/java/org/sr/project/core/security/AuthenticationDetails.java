package org.sr.project.core.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationDetails extends WebAuthenticationDetails {

    private final String branchCode;

    public AuthenticationDetails(HttpServletRequest request){
        super(request);
        branchCode = request.getParameter("branchCode");
    }

    public String getBranchCode(){
        return branchCode;
    }
}
