package com.sti.extractcontrolmodule.model.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ModelStatus {

    ACTIVE(0),
    INACTIVE(1),
    REUSE(2),
    IN_TRAINING(3),
    IN_LOW(4);


    private final int statusCode;
}

