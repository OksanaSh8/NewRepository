package by.itclass.model.dao.newsDAO;

import by.itclass.model.beans.News;
import by.itclass.model.enums.NewsAction;
import by.itclass.model.enums.RatingAction;
import by.itclass.model.exceptions.DAOException;

import java.util.List;

public interface INewsDAO {
    News get(int idNews) throws DAOException;
    List<News> getList(int idUser) throws DAOException;
    boolean save(News news, NewsAction action) throws DAOException;
    boolean updateRating(int idNews, RatingAction action) throws DAOException;
    void delete(int idUser) throws DAOException;
    List<News> getListSortByDate() throws DAOException;
    boolean insertRating(int idUser, int idNews) throws DAOException;


}
