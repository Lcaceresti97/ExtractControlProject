package com.sti.utilitiesmodule.model.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ModelStatus {

    ACTIVE(0),
    INACTIVE(1);

    private final int statusCode;
}
