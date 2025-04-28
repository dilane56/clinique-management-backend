package org.kfokam48.cliniquemanagementbackend.exception;

public class AccessTokenRequiredException extends RuntimeException{
    public AccessTokenRequiredException(String message) {
        super(message);
    }

}
