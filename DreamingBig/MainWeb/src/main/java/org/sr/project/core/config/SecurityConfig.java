package org.sr.project.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.sr.project.core.security.WebAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "org.sr.project.core.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //region private Fields
    @Autowired
    private WebAuthenticationProvider webAuthenticationProvider;
    //endregion
}
