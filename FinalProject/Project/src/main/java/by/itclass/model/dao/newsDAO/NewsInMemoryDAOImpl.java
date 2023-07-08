package by.itclass.model.dao.newsDAO;

import by.itclass.model.beans.News;
import by.itclass.model.enums.NewsAction;
import by.itclass.model.enums.RatingAction;
import by.itclass.model.exceptions.DAOException;
import by.itclass.model.memory.NewsMemory;

import java.util.List;

public class NewsInMemoryDAOImpl implements INewsDAO {
    @Override
    public News get(int idNews) throws DAOException {
        return null;
    }

    @Override
    public List<News> getList(int idUser) {
        return NewsMemory.selectByIdUser(idUser);
    }

    @Override
    public boolean save(News news, NewsAction action) throws DAOException {
        return false;
    }

    @Override
    public boolean updateRating(int idNews, RatingAction action) throws DAOException {
        return false;
    }

    @Override
    public void delete(int idUser) throws DAOException {

    }

    @Override
    public List<News> getListSortByDate() throws DAOException {
        return null;
    }

    @Override
    public boolean insertRating(int idUser, int idNews) throws DAOException {
        return false;
    }
}
