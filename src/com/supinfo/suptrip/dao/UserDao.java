package com.supinfo.suptrip.dao;

import com.supinfo.suptrip.entity.User;

import java.util.List;

public interface UserDao {
    /**
     *
     * @param user
     */
    void addUser(User user);

    /**
     * Update a user
     */
    void updateUser(User user);

    /**
     * Find a user by id
     */
    User findUserById(Long id);

    /**
     * @return an unmodifiable list of all users stored in memory
     */
    List<User> getAllUsers();

    /**
     * Remove a user from the memory
     */
    void removeUser(User user);
}
