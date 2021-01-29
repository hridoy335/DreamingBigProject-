package org.sr.project.core.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.sr.project.core.filters.SitemeshFilter;

import javax.servlet.ServletContext;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    //session
    protected boolean enableHttpSessionEventPublisher(){return true;}

    //sitemesh
    @Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext){
        insertFilters(servletContext,new SitemeshFilter());
    }

}
