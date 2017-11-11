package com.waes.assignment.service.exception;

/**
 * <p>
 * This is the exception used to inform if an entity does not exists in the repository
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructor for the EntityNotFoundException
     * @param message The message explaining the error
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor for the EntityNotFoundException
     * @param message The message explaining the error
     * @param exception The previous exception in the stack
     */
    public EntityNotFoundException(String message, Exception exception) {
        super(message, exception);
    }

}
