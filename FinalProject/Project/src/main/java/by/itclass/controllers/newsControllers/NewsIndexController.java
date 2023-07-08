package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.Image;
import by.itclass.model.beans.News;
import by.itclass.model.exceptions.DAOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@WebServlet(name = "NewsIndexController", value = AppConstant.NEWS_INDEX_CONT)
public class NewsIndexController extends NewsAbstractController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<News> newsList = newsDAO.getListSortByDate();
            List<News> newsListRating = new ArrayList<>();
            newsListRating.addAll(newsList);
            Collections.sort(newsListRating);

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
            request.setAttribute(AppConstant.NEWS_TREESET_ATTR, newsListRating);
            forward(request, response, AppConstant.INDEX_JSP);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
