package ru.kyazimov.soapservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kyazimov.soapservice.entity.User;
import ru.kyazimov.soapservice.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl() {

    }

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        return repository.getByLogin(login);
    }

    @Override
    public boolean deleteUserByLogin(String login) {
        try {
            this.repository.deleteByLogin(login);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User addUser(User user) {
        try {
            return this.repository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            this.repository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
