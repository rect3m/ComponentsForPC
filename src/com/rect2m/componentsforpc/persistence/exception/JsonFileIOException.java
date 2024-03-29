package com.rect2m.componentsforpc.persistence.exception;

/**
 * Відповідає за всі помилки при роботі із Json файлами
 */
public class JsonFileIOException extends RuntimeException {

    public JsonFileIOException(String message) {
        super(message);
    }
}