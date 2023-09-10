package br.com.rafael.accessviewerjava.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{

    private HttpStatus statusCode;

    //quando eu criar um novo AppException, vou passar uma mensagem e um codigo de erro
    public AppException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
