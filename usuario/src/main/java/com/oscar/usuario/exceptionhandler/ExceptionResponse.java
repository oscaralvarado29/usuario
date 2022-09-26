package com.oscar.usuario.exceptionhandler;

public enum ExceptionResponse {
    USER_ALREADY_EXISTS("User already exists"),
    EMAIL_NOT_PRESENT("Email not present"),
    EMAIL_INVALID_FORMAT("Email invalid format"),
    MISSING_MANDATORY_DATA("Missing mandatory data"),
    USER_NOT_FOUND("User not found"),
    PASSWORD_WITHOUT_LOWERCASE_EXCEPTION("Password must contain at least one lowercase letter"),
    PASSWORD_WITHOUT_UPPERCASE_EXCEPTION("Password must contain at least one uppercase letter"),
    PASSWORD_WITHOUT_NUMBER_EXCEPTION("Password must contain at least one number"),
    PASSWORD_WITHOUT_SPECIAL_CHARACTER_EXCEPTION("Password must contain at least one of these characters: *, _, o -"),
    PASSWORD_WITH_CHARACTER_NOT_ALLOWED_EXCEPTION("Password must not contain any of the following characters: space, tab, line feed, carriage return, form feed, or null"),
    PASSWORD_NOT_VALID("Password of size not valid");



    private final String  message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
