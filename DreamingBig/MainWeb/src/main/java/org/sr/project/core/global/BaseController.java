package org.sr.project.core.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.sr.project.core.util.CurrentUser;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BaseController {

    //region PRIVATE FIELDS
    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private ApplicationContext applicationContext;
    //endregion

    //region PROTECTED METHODS
    protected CurrentUser getCurrentUser(HttpServletRequest request){
        return (CurrentUser) request.getSession().getAttribute("currentUser");
    }

    protected String getReportSourcePath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("/WEB-INF/classes/jasperReport/").replace("\\","//");
    }

    protected String getReportOutputPath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("/resources/reports/");
    }

    protected void removeSessionAttribute(HttpServletRequest request, String... attributeName){
        for(String attribute: attributeName){
            request.getSession().removeAttribute(attribute);
        }
    }

    protected void addSessionAttribute(HttpServletRequest request,String attributeName, String setupId){
        request.getSession().setAttribute(attributeName,setupId);
    }

    protected String getSessionAttribute(HttpServletRequest request,String attributeName){
        return (String) request.getSession().getAttribute(attributeName);
    }
    //endregion

    //region PUBLIC METHODS
    public String unmask(String maskedValue){
        return String.join("",maskedValue.split("\\."));
    }

    public String getDateFormat(Date date){
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }
    //endregion
}
