package ru.kyazimov.soapservice.service;

import ru.kyazimov.soapservice.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User getUserByLogin(String login);
    public boolean deleteUserByLogin(String login);
    public User addUser(User user);
    public boolean updateUser(User user);
}
