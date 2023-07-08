package by.itclass.controllers.newsControllers;

import by.itclass.controllers.AbstractController;
import by.itclass.model.dao.newsDAO.INewsDAO;
import by.itclass.model.dao.newsDAO.NewsInMemoryDAOImpl;
import by.itclass.model.dao.newsDAO.NewsMySqlDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewsAbstractController")
public abstract class NewsAbstractController extends AbstractController {
    protected INewsDAO newsDAO;

    public NewsAbstractController() {
        newsDAO = new NewsMySqlDAOImpl();
    }
}
