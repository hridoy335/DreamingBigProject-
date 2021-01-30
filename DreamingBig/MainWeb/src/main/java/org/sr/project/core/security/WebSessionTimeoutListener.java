package org.sr.project.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class WebSessionTimeoutListener implements ApplicationListener<SessionDestroyedEvent> {

    //region PRIVATE FIELDS
    //TODO: User Login Service
    //@Autowired
    //private IUserLoginService userLoginService;
    //endregion

    //region PUBLIC METHODS
    @Override
    public void onApplicationEvent(SessionDestroyedEvent sessionDestroyedEvent) {
        List<SecurityContext> securityContexts = sessionDestroyedEvent.getSecurityContexts();
        //TODO: SecurityPolicyDTO
        //SecurityPolicyDTO securityPolicyDTO  = userLoginService.getSecurityPolicy();

        for(SecurityContext securityContext: securityContexts){
            //TODO: UserLoginDTO
            //UserLoginDTO  userLoginDTO = (UserLoginDTO) securityContext.getAuthentication().getPrincipal();
            InetAddress workStation = null;
            try{
                workStation = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            String ipAddress = workStation.getHostAddress();
            //TODO: UserLoginDTO
            /*if(securityPolicyDTO.getKeepLogoutRecordYn()){
                userLoginService.keepLogoutRecord(userLoginDTO.getUserName(),userLoginDTO.getBranchCode(),ipAddress);
            }*/
        }
    }
    //endregion
}
