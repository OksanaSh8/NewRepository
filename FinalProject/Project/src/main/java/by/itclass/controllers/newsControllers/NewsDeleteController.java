package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewsDeleteController", value = AppConstant.NEWS_DELETE_CONT)
public class NewsDeleteController extends NewsAbstractController  {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(AppConstant.ID_PARAMETER);
        try {
            int idNews = Integer.parseInt(id);
            newsDAO.delete(idNews);
            redirect(response, AppConstant.NEWS_HOME_LIST_CONT);
        } catch (DAOException | NumberFormatException e) {
            e.printStackTrace();
            forward(request, response, AppConstant.MY_NEWS_JSP, e.getMessage());
        }
    }
}
