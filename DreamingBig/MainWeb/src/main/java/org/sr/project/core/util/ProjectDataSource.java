package org.sr.project.core.util;

public class ProjectDataSource {

    public ProjectDataSource(){}

    public String getPassword(String encodedPassword){
        return  DBPasswordProtectorUtil.decrypt(encodedPassword);
    }

    public String getUserName(String encodedUserName){
        return  DBPasswordProtectorUtil.decrypt(encodedUserName);
    }
}
