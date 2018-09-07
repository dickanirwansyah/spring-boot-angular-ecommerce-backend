package com.ecommerce.spring.demoecommerce.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class APIExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e){

        ErrorResponse errors = new ErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()){
            ErrorItem item = new ErrorItem();
            item.setCode(violation.getMessageTemplate());
            item.setMessage(violation.getMessage());
            errors.addError(item);
        }

        /** rollback response status **/
        return new ResponseEntity<ErrorResponse>(errors, HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorItem> handle(ResourceNotFoundException e){

        ErrorItem errorItem = new ErrorItem();

        errorItem.setMessage(e.getMessage());

        return new ResponseEntity<ErrorItem>(HttpStatus.NOT_FOUND);
    }

    /** static class **/
    public  static class ErrorItem{

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String code;

        private String message;

        public String getCode(){
            return code;
        }

        public void setCode(String code){
            this.code = code;
        }

        public String getMessage(){
            return message;
        }

        public void setMessage(String message){
            this.message = message;
        }

    }

    public static class ErrorResponse{

        private List<ErrorItem> errors = new ArrayList<>();

        public List<ErrorItem> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorItem> errors){
            this.errors = errors;
        }

        public void addError(ErrorItem error){
            this.errors.add(error);
        }
    }

}
