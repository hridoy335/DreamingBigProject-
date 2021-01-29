package org.sr.project.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "org.sr.project")
public class WebAuthenticationProvider implements AuthenticationProvider {


    //region PRIVATE FIELDS
    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //TODO: User Login Service
    /*@Autowired
    @Lazy
    private IUserLoginService userLoginService;*/
    //endregion

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName =  String.valueOf(authentication.getPrincipal()).trim();
        String password =  String.valueOf(authentication.getCredentials());

        //UserLoginDTO userLoginDTO =  userLoginService.getUserLoginDetails(userName);

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
