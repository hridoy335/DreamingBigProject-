package org.sr.project.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
public class AccessRightAspect {
    //region PUBLIC METHODS
    @Around("execution(* org.sr.project.*.*.*.index(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
    Object[] parameterList = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) parameterList[0];
        HttpServletResponse reponse = (HttpServletResponse) parameterList[1];
        String requestUrl = String.valueOf(request.getRequestURL());
        String baserUrl =  requestUrl.substring(0,requestUrl.length() - request.getRequestURI().length())+request.getContextPath()+"/";
        String url = requestUrl.replace(baserUrl,"");
        url = url.split("/")[0];

        return joinPoint.proceed();
    }
    //endregion
}
