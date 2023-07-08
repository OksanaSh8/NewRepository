package by.itclass.model.exceptions;

/**
 * Класс описывает ошибки, возникающие на стороне DAO
 */
public class DAOException extends Exception {
    public DAOException() {
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
