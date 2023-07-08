package by.itclass.model.dao.userDAO;

import by.itclass.model.beans.Image;
import by.itclass.model.beans.User;
import by.itclass.model.exceptions.DAOException;
import by.itclass.model.memory.UserMemory;

public class UserInMemoryDAOImpl implements IUserDAO {
    @Override
    public User get(String login, String password) {
        return UserMemory.selectByLoginAndPassword(login, password);
    }

    @Override
    public void uploadImage(int idUser, Image image) throws DAOException {

    }

    @Override
    public boolean add(User user, String password) throws DAOException {
        return false;
    }
}
