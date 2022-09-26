package com.sti.securitymodule.controller;

import com.sti.securitymodule.dto.UserDto;
import com.sti.securitymodule.dto.pageable.PageResponse;
import com.sti.securitymodule.dto.pageable.PageResponseDto;
import com.sti.securitymodule.model.Rol;
import com.sti.securitymodule.model.User;
import com.sti.securitymodule.repository.RolRepository;
import com.sti.securitymodule.repository.UserRepository;
import com.sti.securitymodule.response.BaseResponse;
import com.sti.securitymodule.response.Response;
import com.sti.securitymodule.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

/**
 * Controller for User entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "Users APIs")
@RestController
@RequestMapping(path = "/api/v1/security/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RolRepository rolRepository;


    /**
     * Handler method for saving Validated given User.
     * @return ResponseEntity Response User
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        if(userRepository.existsByUsername(userDto.getUsername())){
            return new ResponseEntity<>("That username already exists", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(userDto.getEmail())){
            return  new ResponseEntity<>("That user email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserStatus(userDto.getUserStatus());

        Rol rol = rolRepository.findByRolName("ROLE_ADMIN").get();
        user.setUserRols(Collections.singletonList(rol));

        userRepository.save(user);
        return  new ResponseEntity<>("Successfully registered user", HttpStatus.OK);

    }

    /**
     * Handler method for fetching a single User by its ID.
     * @param id String
     * @return ResponseEntity User
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<? extends UserDto> findByUserId(@PathVariable final String id) {
        UserDto retrievedUser = userService.findUserById(id);
        return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
    }

    /**
     * Get Paginated sorted User with given criteria.
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse UserDto
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<UserDto>> getUsers(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "id, desc") String[] sort){

        Page<UserDto> userDtoPage = userService
                .findPaginatedSortedUser(page, size, sort);


        PageResponseDto<UserDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(userDtoPage.getSize(), userDtoPage.getNumberOfElements(),
                userDtoPage.getTotalPages(), userDtoPage.getNumber(), userDtoPage.getContent());
    }

    /**
     * Handler method for update Validated given User.
     * @return ResponseEntity Response User
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PutMapping
    public ResponseEntity<? extends Response<UserDto>> updateUser(@RequestBody @Valid UserDto userDto) {
        UserDto updateUser = userService.updateUser(userDto);
        BaseResponse<UserDto> moduleDtoBaseResponse = new BaseResponse<>();
        return moduleDtoBaseResponse
                .buildResponseEntity(HttpStatus.OK, "User Update Successfully", updateUser);
    }

    /**
     * Handler method for deleting a User by its ID.
     * @param id String
     * @return Response null
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<? extends Response<String>> deleteUser(@PathVariable final String id) {
        userService.deleteUser(id);
        BaseResponse<String> userResponse = new BaseResponse<>();
        return userResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("User with ID: ")
                        .append(id)
                        .append(" was deleted.").toString(), id);
    }
}
