package de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory;

/**
 * Exception if something could not be found.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}