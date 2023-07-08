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

@WebServlet(name = "NewsEditController", value = AppConstant.NEWS_EDIT_CONT)
public class NewsEditController extends NewsAbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idNews = Integer.parseInt(request.getParameter(AppConstant.ID_PARAMETER));

        try {

            News news = newsDAO.get(idNews);
            Image pathImage = news.getImage();
          //  System.out.println("imageEDIT"+image);
           /* String pathImage = null;
            if (image.getName() != null && !image.getName().isEmpty()) {
                pathImage = getServletContext().getRealPath("/image")
                        + File.separator + image.getName();
                // System.out.println(pathImage);
                //System.out.println(image);
                try (OutputStream out = new FileOutputStream(pathImage)) {
                    out.write(image.getContext().readAllBytes());
                }
            } else {
                image.setName("/img1.jpg");
            }*/
         request.setAttribute(AppConstant.PATHIMAGE_ATTR, pathImage);
            request.setAttribute(AppConstant.NEWS_ATTR, news);
            getServletContext().getRequestDispatcher(AppConstant.EDIT_NEWS_JSP).forward(request, response);
        } catch (DAOException | NumberFormatException e) {
            e.printStackTrace();
            forward(request, response, AppConstant.MY_NEWS_JSP, e.getMessage());
        }
    }
}
