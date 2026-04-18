package com.project.fitnesstracker.Exceptions.CustomExceptions;

public class CustomException {

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class ActivityNotFoundException extends RuntimeException {
        public ActivityNotFoundException(String message) {
            super(message);
        
    
}

}

}