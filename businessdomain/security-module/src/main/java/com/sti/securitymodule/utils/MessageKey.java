package com.sti.securitymodule.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageKey {

    //Not found.
    PLACE_NOT_FOUND("place-not-found"),
    EMPLOYEE_RECORD_NOT_FOUND("employee-record-not-found"),
    USER_NOT_FOUND("user-not-found"),
    ROL_NOT_FOUND("rol-not-found");

    public final String key;

    public String getKey(){
        return this.key;
    }
}
