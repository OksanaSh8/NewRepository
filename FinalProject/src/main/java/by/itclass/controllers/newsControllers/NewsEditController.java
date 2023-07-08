package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.News;
import by.itclass.model.exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewsEditController", value = AppConstant.NEWS_EDIT_CONT)
public class NewsEditController extends NewsAbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idNews = Integer.parseInt(request.getParameter(AppConstant.ID_PARAMETER));
        try {

            News news = newsDAO.get(idNews);
            request.setAttribute(AppConstant.NEWS_ATTR, news);
            getServletContext().getRequestDispatcher(AppConstant.EDIT_NEWS_JSP).forward(request, response);
        } catch (DAOException | NumberFormatException e) {
            e.printStackTrace();
            forward(request, response, AppConstant.MY_NEWS_JSP, e.getMessage());
        }
    }
}
