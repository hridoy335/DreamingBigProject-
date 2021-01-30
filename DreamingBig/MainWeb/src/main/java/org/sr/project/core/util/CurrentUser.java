package org.sr.project.core.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CurrentUser {
    //region PRIVATE FIELDS
    private String userId;
    private String userName;
    private String fullUserName;
    private Date loggedOn;
    private Date serverDate;
    //endregion

    //region GETTER & SETTER

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public Date getLoggedOn() {
        return loggedOn;
    }

    public void setLoggedOn(Date loggedOn) {
        this.loggedOn = loggedOn;
    }

    public Date getServerDate() {
        return serverDate;
    }

    public void setServerDate(Date serverDate) {
        this.serverDate = serverDate;
    }

    //endregion
}
