package ru.kyazimov.soapservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kyazimov.soapservice.entity.User;

public interface UserEntityRepository extends CrudRepository<User, Long> {

    public User findByLogin(String login);
    public void deleteByLogin(String login);
}
