package org.sr.project.core.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class WebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    //region PRIVATE FIELDS
    private String branchCode;
    //endregion

    //region PUBLIC METHODS
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        AuthenticationDetails authenticationDetails = new AuthenticationDetails(request);
        branchCode = authenticationDetails.getBranchCode();
        return authenticationDetails;
    }

    public String getBranchCode(){
        return branchCode;
    }
    //endregion
}
