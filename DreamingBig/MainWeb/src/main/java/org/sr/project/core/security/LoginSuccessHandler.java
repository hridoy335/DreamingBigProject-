package org.sr.project.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    //region PRIVATE FIELDS
    //TODO: userLoginService
    //@Autowired
    //private IUserLoginService userLoginService;
    //endregion

    //region CONSTRUCTOR
    public LoginSuccessHandler(String defaultTargetPath){
        super.setDefaultTargetUrl(defaultTargetPath);
    }
    //endregion

    //region PUBLIC METHODS
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException{
        //UserLoginDTO userLoginDTO  = (UserLoginDTO) authentication.getPrincipal();

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(20*60);

        InetAddress workStation = InetAddress.getLocalHost();
        String ipAddress = workStation.getHostAddress();
        //save User Login Records Here

    }
    //endregion
}
