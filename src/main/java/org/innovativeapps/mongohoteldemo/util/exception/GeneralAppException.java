package org.innovativeapps.mongohoteldemo.util.exception;

public class GeneralAppException extends AppException {

    private static final long serialVersionUID = 1L;

    public GeneralAppException(int status, int code, String message,
                               String developerMessage, String link) {
        super(status, code, message, developerMessage, link);
    }
}
