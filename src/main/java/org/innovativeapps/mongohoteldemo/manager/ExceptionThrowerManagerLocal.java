package org.innovativeapps.mongohoteldemo.manager;

import org.innovativeapps.mongohoteldemo.util.exception.GeneralAppException;

public interface ExceptionThrowerManagerLocal {

    void throwInvalidTokenException (String link) throws GeneralAppException;

    void throwHotelNotFoundException (String link) throws GeneralAppException;

    void throwUserNotFoundException (String link) throws GeneralAppException;

    void throwGeneralException (String link) throws GeneralAppException;

    void throwNullParameterException (String link) throws GeneralAppException;

    void throwInvalidIntegerAttributeException(String link) throws GeneralAppException;
}
