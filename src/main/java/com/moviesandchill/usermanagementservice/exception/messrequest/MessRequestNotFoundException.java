package com.moviesandchill.usermanagementservice.exception.messrequest;

public class MessRequestNotFoundException extends Exception{

    public MessRequestNotFoundException() {
    }

    public MessRequestNotFoundException(long messRequestId) {
        super("messRequest with id: " + messRequestId + " not found");
    }

    public MessRequestNotFoundException(String message) {
        super(message);
    }
}
