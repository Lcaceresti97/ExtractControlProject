package com.sti.securitymodule.service.implementation;

import com.sti.securitymodule.dto.UserDto;
import com.sti.securitymodule.exception.UserNotFoundException;
import com.sti.securitymodule.model.User;
import com.sti.securitymodule.model.mappers.UserMapper;
import com.sti.securitymodule.model.status.ModelStatus;
import com.sti.securitymodule.repository.UserRepository;
import com.sti.securitymodule.service.UserService;
import com.sti.securitymodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for User entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = User.buildFromDtoUser(this.userMapper.dtoToUser(userDto));
        this.userRepository.save(user);
        return userMapper.userToDto(user);
    }

    @Override
    public UserDto findUserById(String id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.buildUserNotFoundExceptionForId(id));
        return userMapper.userToDto(isActiveUser(user,"id", id));
    }

    @Override
    public Page<UserDto> findPaginatedSortedUser(int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<UserDto> userDtoList;
        userDtoList = userMapper
                .userToDto(userRepository.findAll(pageable).toList());

        return new PageImpl<>(userDtoList);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = this.userMapper.dtoToUser(userDto);
        this.userRepository.save(user);
        return userMapper.userToDto(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = userMapper.dtoToUser(findUserById(id));
        user.setUserStatus(ModelStatus.INACTIVE);
        userRepository.save(user);
    }

    /**
     * Return User if status code is ACTIVE.
     * @param user User
     * @param queryField String
     * @param queryFieldValue String
     * @return User
     * @throws UserNotFoundException ex
     */
    private User isActiveUser(User user, String queryField, String queryFieldValue){
        if(user.getUserStatus().getStatusCode() == 0){
            return user;
        }
        throw UserNotFoundException
                .buildUserNotFoundExceptionForField(queryField, queryFieldValue);
    }
}
