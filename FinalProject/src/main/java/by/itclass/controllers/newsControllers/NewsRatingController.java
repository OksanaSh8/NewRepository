package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.News;
import by.itclass.model.beans.User;
import by.itclass.model.enums.EnumManager;
import by.itclass.model.enums.RatingAction;
import by.itclass.model.exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "NewsRatingController", value = AppConstant.NEWS_RATING_CONT)
public class NewsRatingController extends NewsAbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(AppConstant.ID_PARAMETER);
        int id_new = Integer.parseInt(id);
        String action = request.getParameter(AppConstant.ACTION_PARAMETER);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AppConstant.USER_ATTR);

        //upp, down
        try {
            int idNews = Integer.parseInt(id);
            if (user!=null&&newsDAO.insertRating(user.getId(), id_new)){
                RatingAction ratingAction = EnumManager.getKindRatingAction(action);
                if (user!=null&&newsDAO.updateRating(idNews, ratingAction)){
                    forward(request, response, AppConstant.NEWS_VIEW_CONT);
                }
            }else {
                News news = newsDAO.get(idNews);
                request.setAttribute(AppConstant.NEWS_ATTR, news);
                forward(request, response, AppConstant.NEWS_JSP, AppConstant.NEWS_RATING_IS_NOT_UPDATE);
            }

        }catch (DAOException | NumberFormatException e) {
            e.printStackTrace();
            forward(request, response, AppConstant.NEWS_JSP, AppConstant.NEWS_RATING_IS_NOT_UPDATE);
        }
    }
}
