package com.example.demo.exception;

/**
 * Исключение, выбрасываемое при отсутствии запрашиваемой сущности в базе данных.
 * Соответствует HTTP статусу 404 Not Found.
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
