package org.sr.project.core.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class AuthController {

    //region PUBLIC METHODS
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error",required=false) String error, Model model, HttpServletRequest request){
        if(error!=null){
            model.addAttribute("error",getErrorMessage(request,"SPRING_SECURITY_LAT_EXCEPTION"));
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "login";
    }

    @PreAuthorize("isAnonynmous()")
    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public ModelAndView auth(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        return "redirect:/login";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "403",method = RequestMethod.GET)
    public ModelAndView accessDenied(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView model = new ModelAndView();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
        //TODO: USER LOGIN DTO CREATION
            //UserLoginDTO userLoginDTO = (UserLoginDTO) authentication.getPrincipal();
            //model.addObject("userName", userLoginDTO.getUserName());
        }
        model.setViewName("403");

        return model;
    }
    //endregion

    //region PRIVATE METHODS
    public String getErrorMessage( HttpServletRequest request, String key){
        Exception exception = (Exception) request.getSession().getAttribute(key);
        if(exception!=null){
            String message = exception.getMessage();
            //TODO : LOGIN ERROR CODE ENUM CREATION
            /*if(message.equals(LoginErrorCode.FALIED.getCode()) || message.equals(LoginErrorCode.LOCKED.getCode())){
                return message;
            }else{
                return LoginErrorCode.MAX_SESSION.getCode();
            }*/
            return null;
        }else{
            return null;
        }
    }
    //endregion
}
