package ru.kyazimov.soapservice.service;

import ru.kyazimov.soapservice.entity.User;

import java.util.List;

public interface UserEntityService {

    public List<User> getAllUsers();
    public User getUser(String login);
    public void deleteUser(String login);
    public void addUser(User user);
    public void updateUser(User user);
}
