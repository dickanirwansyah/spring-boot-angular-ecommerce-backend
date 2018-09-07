package com.ecommerce.spring.demoecommerce.exception;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 5861310537366287163L;

    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }
}
