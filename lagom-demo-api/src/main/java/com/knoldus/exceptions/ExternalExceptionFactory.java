package com.knoldus.exceptions;

import org.apache.commons.lang3.StringUtils;

public class ExternalExceptionFactory {
    
    public static Throwable getInstance(Fault fault) {
        if (fault == null)
            return null;
        return determineException(fault);
    }
    
    private static Throwable determineException(Fault fault) {
        String message = fault.getErrorMessage();
        
        if (StringUtils.isNotEmpty(message) && message.contains("Requires authentication")) {
            return new AuthenticationException(fault);
        }
        return new GenericException(fault);
    }
    
    public static class AuthenticationException extends RuntimeException {
        
        Fault fault;
        
        public AuthenticationException(Fault fault) {
            this.fault = fault;
        }
    }
    
    public static class GenericException extends RuntimeException {
        
        Fault fault;
        
        public GenericException(Fault fault) {
            this.fault = fault;
        }
    }
}

