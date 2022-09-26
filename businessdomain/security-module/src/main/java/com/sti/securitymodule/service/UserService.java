package com.sti.securitymodule.service;

import com.sti.securitymodule.dto.UserDto;
import com.sti.securitymodule.exception.UserNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Service interface for User entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface UserService {

    /**
     * Saves given User into DB.
     * @param userDto User
     */
    UserDto saveUser(UserDto userDto);

    /**
     * Find User by its ID.
     * @param id String
     * @return User userDto
     * @throws UserNotFoundException when no User is found by ID
     */
    UserDto findUserById(final String id) throws UserNotFoundException;

    /**
     * Return a page of sorted User.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto User.
     */
    Page<UserDto> findPaginatedSortedUser(int page, int size, String[] sort);


    /**
     * Update given User into DB.
     * @param userDto User
     */
    UserDto updateUser(UserDto userDto);

    /**
     * Delete User by its ID.
     * @param id
     */
    void deleteUser(final String id);

}
