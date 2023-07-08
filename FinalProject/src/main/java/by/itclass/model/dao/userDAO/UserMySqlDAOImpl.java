package by.itclass.model.dao.userDAO;

import by.itclass.model.beans.Image;
import by.itclass.model.beans.User;
import by.itclass.model.db.ConnectionManager;
import by.itclass.model.db.SQLRequest;
import by.itclass.model.exceptions.DAOException;

import java.io.InputStream;
import java.sql.*;

public class UserMySqlDAOImpl implements IUserDAO {
    @Override
    public User get(String login, String password) throws DAOException {
        User user = null;
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.SELECT_USER_BY_LOGIN_AND_PASSWORD);
             PreparedStatement pst2 = cn.prepareStatement(SQLRequest.SELECT2_USER_BY_LOGIN_AND_PASSWORD)) {
            pst.setString(1, login);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int idUser = rs.getInt(SQLRequest.ID_USER_COLUMN);
                String email = rs.getString(SQLRequest.EMAIL_COLUMN);
                int idImage = rs.getInt(SQLRequest.ID_IMAGE_COLUMN);
                String name = rs.getString(SQLRequest.NAME_COLUMN);
                InputStream context = rs.getBinaryStream(SQLRequest.CONTEXT_COLUMN);
                Image image = new Image(idImage, name, context);
                user = new User(idUser, login, email, image);

            }
            if (user == null) {
                pst2.setString(1, login);
                pst2.setString(2, password);
                ResultSet rs2 = pst2.executeQuery();
                if (rs2.next()) {
                    int id = rs2.getInt(SQLRequest.ID_COLUMN);
                    String email = rs2.getString(SQLRequest.EMAIL_COLUMN);
                    user = new User(id, login, email);

                }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public void uploadImage(int idUser, Image image) throws DAOException {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.INSERT_IMAGE_FOR_USER)) {
            pst.setInt(1, idUser);
            pst.setString(2, image.getName());
            pst.setBlob(3, image.getContext());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean add(User user, String password) throws DAOException{
        boolean isUpdate = false;
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement("insert  into users(login, " +
                     "password, email) " + "values (?, ?, ?)")) {
            pst.setString(1, user.getLogin());
            pst.setString(2, password);
            pst.setString(3, user.getEmail());
            isUpdate = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return isUpdate;
    }
}
