package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.Image;
import by.itclass.model.beans.News;
import by.itclass.model.exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "NewsViewController", value = AppConstant.NEWS_VIEW_CONT)
public class NewsViewController extends NewsAbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter(AppConstant.ID_PARAMETER);
        try {
            int idNews = Integer.parseInt(id);
            News news = newsDAO.get(idNews);
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
            request.setAttribute(AppConstant.NEWS_ATTR, news);
            forward(request, response, AppConstant.NEWS_JSP);
        } catch (DAOException | NumberFormatException e) {
            e.printStackTrace();
            forward(request, response, AppConstant.MY_NEWS_JSP, e.getMessage());
        }
    }
}
