package org.sr.project.core.filters;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.ConcurrentSessionFilter;

public class CustomConcurrentSessionFilter extends ConcurrentSessionFilter {
    public CustomConcurrentSessionFilter(SessionRegistry sessionRegistry){
        super(sessionRegistry);
    }
}
