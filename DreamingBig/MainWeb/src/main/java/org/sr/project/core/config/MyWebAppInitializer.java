package org.sr.project.core.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.sr.project.core.filters.SitemeshFilter;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private int maxUploadSizeInMb = 5*1024*1024; // 5 MB

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class,SecurityConfig.class, SitemeshFilter.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                maxUploadSizeInMb, maxUploadSizeInMb*2,maxUploadSizeInMb/2);

        registration.setMultipartConfig(multipartConfigElement);
        registration.setLoadOnStartup(1);
    }

}
