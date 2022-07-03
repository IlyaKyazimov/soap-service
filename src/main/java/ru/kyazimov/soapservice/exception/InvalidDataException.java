package ru.kyazimov.soapservice.exception;

public class InvalidDataException extends AbstractException{
    public InvalidDataException(String message, String techInfo) {
        super(message, techInfo);
    }
}
