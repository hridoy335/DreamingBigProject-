package org.sr.project.core.enumeration;

public enum LoginErrorCode {

    FAILED("userLogin.failed"),
    LOCKED("userLogin.locked"),
    MAX_SESSION("userLogin.maxSession");

    private final String code;

    private LoginErrorCode(String code){
        this.code = code;
    }

    public String getCode(){return code;}
}
