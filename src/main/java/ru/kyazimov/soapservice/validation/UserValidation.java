package ru.kyazimov.soapservice.validation;

import ru.kyazimov.soapservice.entity.User;
import ru.kyazimov.soapservice.exception.EmptyDataException;
import ru.kyazimov.soapservice.exception.InvalidDataException;
import ru.kyazimov.soapservice.exception.InvalidPasswordException;

public class UserValidation {

    public static void userValidation(String login, String password, String name) {
        if (login.length() == 0 || password.length() == 0 || name.length() == 0) {
            throw new EmptyDataException("Fields 'name', 'login' and 'password' cannot be empty", "invalid data");
        }

        if (!password.matches(".*\\d+.*") || !password.matches(".*[A-Z].*")) {
            throw new InvalidPasswordException("Password should contain an uppercase letter and a number", "invalid password");
        }
    }

    public static void isUserExist(User user) {
        if (user == null) {
            throw new InvalidDataException("User with this login does not exist", "user does not exist");
        }
    }

    public static void isUserAlreadyExist(User user) {
        if (user != null) {
            throw new InvalidDataException("User with login " + user.getLogin() + " already exists", "user already exists");
        }
    }
}
