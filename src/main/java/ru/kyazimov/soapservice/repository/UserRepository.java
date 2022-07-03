package ru.kyazimov.soapservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kyazimov.soapservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getByLogin(String login);
    void deleteByLogin(String login);
}
