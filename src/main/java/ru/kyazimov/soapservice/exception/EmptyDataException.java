package ru.kyazimov.soapservice.exception;

public class EmptyDataException extends AbstractException {
    public EmptyDataException(String message, String techInfo) {
        super(message, techInfo);
    }
}
