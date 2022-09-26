package com.sti.securitymodule.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.securitymodule.model.EmployeeFile;
import com.sti.securitymodule.model.Rol;
import com.sti.securitymodule.model.User;
import com.sti.securitymodule.model.status.ModelStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.UsesSunHttpServer;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@JsonSerialize
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a User data that user receive when make a request method")
public class UserDto {

    @JsonProperty
    @ApiModelProperty(name = "id", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of User after it's created")
    private String id;

    @JsonProperty()
    @ApiModelProperty(name = "name", required = true, example = "Carlos Flores", value = "Name of User")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    @JsonProperty()
    @ApiModelProperty(name = "username", required = true, example = "carlos.flores", value = "Username of User")
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 150)
    private String username;

    @JsonProperty()
    @ApiModelProperty(name = "email", required = true, example = "carlos.flores@gmail.com", value = "Email of User")
    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 64)
    @Email(message = "User email must be valid")
    private String email;

    @JsonProperty()
    @ApiModelProperty(name = "password", required = true, example = "*******", value = "Password of User")
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 32)
    private String password;

    @JsonProperty("userStatus")
    @ApiModelProperty(name = "userStatus", example = "ACTIVE OF INACTIVE", value = "Status")
    private ModelStatus userStatus;

    @JsonProperty("userRols")
    @Builder.Default
    private List<Rol> userRols = new ArrayList<>();

    @JsonProperty("employeeFileUser")
    private User user;

    @JsonIgnore
    public List<Rol> getUserRols() {
        return userRols;
    }

    @JsonIgnore
    public void setUserRols(List<Rol> userRols) {
        this.userRols = userRols;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }
}
