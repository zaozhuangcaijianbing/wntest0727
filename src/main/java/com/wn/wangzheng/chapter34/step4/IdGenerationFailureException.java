package com.wn.wangzheng.chapter34.step4;

public class IdGenerationFailureException extends Exception {
    public IdGenerationFailureException() {
        super();
    }

    public IdGenerationFailureException(String message) {
        super(message);
    }

    public IdGenerationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
