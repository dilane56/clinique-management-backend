package org.kfokam48.cliniquemanagementbackend.exception;

public class RessourceNotFoundException extends  RuntimeException {
    public RessourceNotFoundException(String message) {
        super(message);
    }
}
