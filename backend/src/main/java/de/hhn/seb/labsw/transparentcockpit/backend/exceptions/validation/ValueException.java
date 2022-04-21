package de.hhn.seb.labsw.transparentcockpit.backend.exceptions.validation;

/**
 * Exception if Something is wrong with the values.
 */
public class ValueException extends RuntimeException {

    public ValueException(String message) {
        super(message);
    }
}
