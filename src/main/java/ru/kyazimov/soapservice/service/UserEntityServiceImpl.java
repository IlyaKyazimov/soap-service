package ru.kyazimov.soapservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kyazimov.soapservice.entity.User;
import ru.kyazimov.soapservice.repository.UserEntityRepository;

import java.util.ArrayList;
import java.util.List;

public class UserEntityServiceImpl implements UserEntityService {

    private UserEntityRepository repository;

    public UserEntityServiceImpl() {

    }

    @Autowired
    public UserEntityServiceImpl(UserEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(u -> users.add(u));
        return users;
    }

    @Override
    public User getUser(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public void deleteUser(String login) {
        repository.deleteByLogin(login);
    }

    @Override
    public void addUser(User user) {
        repository.save(user);
    }

    @Override
    public void updateUser(User user) {
        repository.save(user);
    }
}
