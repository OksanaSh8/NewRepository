package by.itclass.model.dao.userDAO;

import by.itclass.model.beans.Image;
import by.itclass.model.beans.User;
import by.itclass.model.exceptions.DAOException;

public interface IUserDAO {
    User get(String login, String password) throws DAOException;
    void uploadImage(int idUser, Image image) throws DAOException;
    boolean add(User user, String password) throws DAOException;
}
