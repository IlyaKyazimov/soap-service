package ru.kyazimov.soapservice.exception;

public class InvalidPasswordException extends AbstractException {
    public InvalidPasswordException(String message, String techInfo) {
        super(message, techInfo);
    }
}
