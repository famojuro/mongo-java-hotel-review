package org.innovativeapps.mongohoteldemo.manager;

import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

@Stateless
public class ExceptionThrowerManager implements ExceptionThrowerManagerLocal{

    private final String USER_NOT_FOUND_ERROR = "User with the specified ID could not be found";
    private final String USER_NOT_FOUND_ERROR_DETAILS = "User with the specified ID could not be found";

    private final String INVALID_TOKEN_ERROR = "Invalid or expired token supplied";
    private final String INVALID_TOKEN_ERROR_DETAILS = "The auth token supplied is invalid or expired";

    private final String ERROR = "An exception occured.";
    private final String ERROR_DETAILS = "Error details: ";

    private final String NULL_PARAMETER_ERROR = "Some required parameters are null";
    private final String NULL_PARAMETER_ERROR_DETAILS = "Please be sure to provide the required options";

    private final String INVALID_INTEGER_PARAMETER_ERROR = "Required integer type parameter not provided";
    private final String INVALID_INTEGER_PARAMETER_ERROR_DETAILS = "A number is expected, please provided a valid number";

    private final String HOTEL_NOT_FOUND_ERROR = "Hotel with the specified ID could not be found";
    private final String HOTEL_NOT_FOUND_ERROR_DETAILS = "Hotel with the specified ID could not be found";

    @Override
    public void throwInvalidTokenException(String link) throws GeneralAppException {
        throw new GeneralAppException(Response.Status.BAD_REQUEST.getStatusCode(),
                400, INVALID_TOKEN_ERROR, INVALID_TOKEN_ERROR_DETAILS,
                link);
    }

    @Override
    public void throwUserNotFoundException(String link) throws GeneralAppException {
        throw new GeneralAppException(Response.Status.BAD_REQUEST.getStatusCode(),
                400, USER_NOT_FOUND_ERROR, USER_NOT_FOUND_ERROR_DETAILS,
                link);

    }

    @Override
    public void throwHotelNotFoundException(String link) throws GeneralAppException {
        throw new GeneralAppException(Response.Status.BAD_REQUEST.getStatusCode(),
                400, HOTEL_NOT_FOUND_ERROR, HOTEL_NOT_FOUND_ERROR_DETAILS,
                link);
    }

    @Override
    public void throwGeneralException (String link) throws GeneralAppException {
        throw new GeneralAppException(Response.Status.BAD_REQUEST.getStatusCode(),
                400, ERROR, ERROR_DETAILS,
                link);
    }

    @Override
    public void throwNullParameterException(String link) throws GeneralAppException {
        throw new GeneralAppException(Response.Status.BAD_REQUEST.getStatusCode(),
                400, NULL_PARAMETER_ERROR, NULL_PARAMETER_ERROR_DETAILS,
                link);
    }

    @Override
    public void throwInvalidIntegerAttributeException(String link) throws GeneralAppException {
        throw new GeneralAppException(Response.Status.BAD_REQUEST.getStatusCode(),
                400, INVALID_INTEGER_PARAMETER_ERROR, INVALID_INTEGER_PARAMETER_ERROR_DETAILS,
                link);
    }
}
