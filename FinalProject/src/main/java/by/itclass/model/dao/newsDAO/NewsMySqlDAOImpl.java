package by.itclass.model.dao.newsDAO;
import by.itclass.model.beans.Image;
import by.itclass.model.beans.News;
import by.itclass.model.db.ConnectionManager;
import by.itclass.model.db.SQLRequest;
import by.itclass.model.enums.NewsAction;
import by.itclass.model.enums.RatingAction;
import by.itclass.model.exceptions.DAOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsMySqlDAOImpl implements INewsDAO {
    @Override
    public News get(int idNews) throws DAOException {
        News news = null;
        try (Connection cn = ConnectionManager.getConnection();
            PreparedStatement pst = cn.prepareStatement(SQLRequest.SELECT_NEWS_BY_ID)) {
            pst.setInt(1, idNews);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int idUser=rs.getInt(SQLRequest.ID_USER_COLUMN);
                String title = rs.getString(SQLRequest.TITLE_COLUMN);
                String text = rs.getString(SQLRequest.TEXT_COLUMN);
                String date= String.valueOf(rs.getTimestamp(SQLRequest.DATE_COLUMN));
                String date1=date.substring(0,16);
                int rating=rs.getInt(SQLRequest.RATING_COLUMN);
                String name = rs.getString(SQLRequest.NAME_COLUMN);
                InputStream context = rs.getBinaryStream(SQLRequest.CONTEXT_COLUMN);
                Image image = new Image(name, context);
                news = new News(idNews, idUser, title, text, image, date1, rating);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return news;
    }

    @Override
    public List<News> getList(int idUser) throws DAOException {
        List<News> newsList = new ArrayList<>();
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.SELECT_NEWS_LIST_BY_ID_USER)) {
            pst.setInt(1, idUser);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(SQLRequest.ID_COLUMN);
                String title = rs.getString(SQLRequest.TITLE_COLUMN);
                String text = rs.getString(SQLRequest.TEXT_COLUMN);
                String date= String.valueOf(rs.getTimestamp(SQLRequest.DATE_COLUMN));
                String date1=date.substring(0,16);
                String name = rs.getString(SQLRequest.NAME_COLUMN);
                InputStream context = rs.getBinaryStream(SQLRequest.CONTEXT_COLUMN);
                Image image = new Image(name, context);
                newsList.add(new News(id, idUser, title, text, image, date1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return newsList;
    }

    @Override
    public boolean save(News news, NewsAction action) throws DAOException {
        boolean isSave = false;
        System.out.println("save"+news);
        final String SQL = action.getSql();
        try (Connection cn = ConnectionManager.getConnection();
            PreparedStatement pst = cn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, news.getTitle());
            pst.setString(2, news.getText());
            pst.setTimestamp(3, null);
            pst.setInt(4, news.getIdUser());
            if (action == NewsAction.EDIT) {
                pst.setInt(5, news.getId());
            }
            isSave = pst.executeUpdate() > 0;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int idNews = rs.getInt(1);
                uploadImage(idNews, news.getImage());
            }
            else if (action == NewsAction.EDIT) {
                editImage(news.getId(), news.getImage());
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return isSave;
    }

    @Override
    public boolean updateRating(int idNews, RatingAction action) throws DAOException {
        boolean isUpdate = false;
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.UPDATE_NEWS_RATING_BY_ID)) {
            pst.setInt(1, action.getValue());
            pst.setInt(2, idNews);
            isUpdate = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return isUpdate;
    }
    @Override
    public void delete(int idUser) throws DAOException {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.DELETE_NEWS_BY_ID)) {
            pst.setInt(1, idUser);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public List<News> getListSortByDate() throws DAOException {
        List<News> newsList = new ArrayList<>();

        try (Connection cn = ConnectionManager.getConnection();
            PreparedStatement pst = cn.prepareStatement(SQLRequest.SELECT_NEWS_LIST_BY_DATE)) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id=rs.getInt(SQLRequest.ID_COLUMN);
                String title = rs.getString(SQLRequest.TITLE_COLUMN);
                String text = rs.getString(SQLRequest.TEXT_COLUMN);
                String date= String.valueOf(rs.getTimestamp(SQLRequest.DATE_COLUMN));
                String date1=date.substring(0,16);
                int rating=rs.getInt(SQLRequest.RATING_COLUMN);
                String name = rs.getString(SQLRequest.NAME_COLUMN);
                InputStream context = rs.getBinaryStream(SQLRequest.CONTEXT_COLUMN);
                Image image = new Image(name, context);
                newsList.add(new News(id, title, text, image, date1, rating));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return newsList;
    }

    @Override
    public boolean insertRating(int idUser, int idNews) throws DAOException {
        boolean isUpdate = false;
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.INSERT_RATING)) {
            pst.setInt(1, idUser);
            pst.setInt(2, idNews);
            isUpdate = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return isUpdate;
    }

    private void uploadImage(int idNews, Image image) throws SQLException {
        try (Connection cn = ConnectionManager.getConnection();
            PreparedStatement pst = cn.prepareStatement(SQLRequest.INSERT_IMAGE_FOR_NEWS)) {
            pst.setInt(1, idNews);
            pst.setString(2, image.getName());
            pst.setBlob(3, image.getContext());
            pst.executeUpdate();
        }
    }

   private void editImage(int idNews, Image image) throws SQLException {
        try (Connection cn = ConnectionManager.getConnection();
            PreparedStatement pst = cn.prepareStatement(SQLRequest.UPDATE_IMAGE_FOR_NEWS)) {
            pst.setString(1, image.getName());
            pst.setBlob(2, image.getContext());
            pst.setInt(3, idNews);
            pst.executeUpdate();
        }
    }


}
