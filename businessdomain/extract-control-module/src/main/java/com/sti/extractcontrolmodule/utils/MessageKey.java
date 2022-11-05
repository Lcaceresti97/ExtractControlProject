package com.sti.extractcontrolmodule.utils;

import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageKey {

    //Not found.
    STATUS_CC_NOT_FOUND("status-cc-not-found"),
    ACTIVITY_NOT_FOUND("activity-record-not-found"),
    TYPES_OF_BOOKS_NOT_FOUND("types-of-books-not-found"),
    TRACT_NOT_FOUND("tract-not-found"),
    HORIZONTAL_NOT_FOUND("horizontal-not-found");

    public final String key;

    public String getKey(){
        return this.key;
    }
}
