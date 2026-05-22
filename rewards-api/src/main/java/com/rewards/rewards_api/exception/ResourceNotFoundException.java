package com.rewards.rewards_api.exception;


/**
 * Exception thrown when resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
