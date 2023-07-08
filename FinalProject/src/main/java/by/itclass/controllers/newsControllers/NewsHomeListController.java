package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.Image;
import by.itclass.model.beans.News;
import by.itclass.model.beans.User;
import by.itclass.model.exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(name = "NewsHomeListController", value = AppConstant.NEWS_HOME_LIST_CONT)
public class NewsHomeListController extends NewsAbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AppConstant.USER_ATTR);

        try {
            List<News> newsList = newsDAO.getList(user.getId());

            for (News news : newsList) {
                //Обработка картинки
                Image image = news.getImage();
                if (image.getName() != null && !image.getName().isEmpty()) {
                    String pathImage = getServletContext().getRealPath("/image")
                            + File.separator + image.getName();
                   // System.out.println(pathImage);
                    //System.out.println(image);
                    try (OutputStream out = new FileOutputStream(pathImage)) {
                        out.write(image.getContext().readAllBytes());
                    }
                } else {
                    image.setName("/img1.jpg");
                }
                //
            }
            request.setAttribute(AppConstant.NEWS_LIST_ATTR, newsList);
            forward(request, response, AppConstant.MY_NEWS_JSP);
        } catch (DAOException e) {
            e.printStackTrace();
            forward(request, response, AppConstant.MY_NEWS_JSP, e.getMessage());
        }
    }
}
