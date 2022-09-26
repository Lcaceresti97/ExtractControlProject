package com.sti.securitymodule.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * Login DTO class to encapsulate implementation of Login entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Getter
@Setter
public class LoginDto {

    private String usernameOrEmail;

    private String password;
}
