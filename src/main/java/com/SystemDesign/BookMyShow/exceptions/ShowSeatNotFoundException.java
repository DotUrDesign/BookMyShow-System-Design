package com.SystemDesign.BookMyShow.exceptions;

public class ShowSeatNotFoundException extends RuntimeException {
    public ShowSeatNotFoundException(String message) {
        super(message);
    }
}
