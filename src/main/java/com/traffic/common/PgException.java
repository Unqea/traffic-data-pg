package com.traffic.common;

public class PgException extends RuntimeException {
    private final String code;

    public PgException(String code) {
        super((String)null, (Throwable)null, false, false);
        this.code = code;
    }

    public PgException(String code, String message) {
        super(message, (Throwable)null, false, false);
        this.code = code;
    }


    public String getCode() {
        return this.code;
    }
}
