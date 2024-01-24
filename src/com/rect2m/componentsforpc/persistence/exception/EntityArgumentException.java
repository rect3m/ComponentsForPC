package com.rect2m.componentsforpc.persistence.exception;

import java.util.List;

/**
 * Custom exception class for handling cases where the input arguments of entity fields are
 * invalid.
 * <p>
 * This exception extends {@link IllegalArgumentException} and includes a list of error messages
 * indicating the specific validation issues with the entity fields.
 *
 * @author Rosada Stanislav
 * @version 1.0
 */
public class EntityArgumentException extends IllegalArgumentException {

    /**
     * List of error messages describing the validation issues with the entity fields.
     */
    private final List<String> errors;

    /**
     * Constructs a new {@code EntityArgumentException} with the specified list of error messages.
     *
     * @param errors the list of error messages indicating validation issues with entity fields
     */
    public EntityArgumentException(List<String> errors) {
        this.errors = errors;
    }

    /**
     * Retrieves the list of error messages associated with this exception.
     *
     * @return the list of error messages
     */
    public List<String> getErrors() {
        return errors;
    }
}