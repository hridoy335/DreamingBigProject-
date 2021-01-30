package org.sr.project.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class LogoutFailureHandler implements ApplicationListener<ContextRefreshedEvent> {

    //region PRIVATE FIELDS
    //TODO: userLoginService
    //@Autowired
    //private IUserLoginService userLoginService;
    //endregion

    //region PUBLIC METHODS
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //userLoginService.keepLogoutRecord();
    }
    //endregion
}
