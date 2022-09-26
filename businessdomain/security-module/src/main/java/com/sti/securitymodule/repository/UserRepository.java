package com.sti.securitymodule.repository;

import com.sti.securitymodule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for User entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Find User by its userName or email.
     * @param username String
     * @param email String
     * @return Optional User
     */
    public Optional<User> findByUsernameOrEmail(String username, String email);

    /**
     * Find if a user exists by their userName.
     * @param username String
     */
    public Boolean existsByUsername(String username);

    /**
     * Find if a user exists by their email.
     * @param email String
     */
    public Boolean existsByEmail(String email);
}
