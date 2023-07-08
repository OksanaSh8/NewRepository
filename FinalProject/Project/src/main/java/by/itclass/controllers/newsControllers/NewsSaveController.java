package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.Image;
import by.itclass.model.beans.News;
import by.itclass.model.beans.User;
import by.itclass.model.enums.EnumManager;
import by.itclass.model.enums.NewsAction;
import by.itclass.model.exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


//Добавление
//Редактирование
@WebServlet(name = "NewsSaveController", value = AppConstant.SAVE_NEWS_CONT)
public class NewsSaveController extends NewsAbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = (String) request.getAttribute(AppConstant.TITLE_PARAMETER);
        String text = (String) request.getAttribute(AppConstant.TEXT_PARAMETER);
        String action = (String) request.getAttribute(AppConstant.ACTION_PARAMETER);
        String id = (String) request.getAttribute(AppConstant.ID_PARAMETER);
        int id_new = 0;
        if (id != null) {
            id_new = Integer.parseInt(id);
        }

        //  System.out.println("imageName"+imageName);
        Image image = (Image) request.getAttribute(AppConstant.IMAGE_ATTR);
        System.out.println("imageSave"+image);




        if (image.getName().isEmpty()) {

           // String imageName = (String) request.getAttribute(AppConstant.IMAGE_NAME_PARAMETER);
            image = (Image) request.getAttribute(AppConstant.IMAGE_PARAMETER);
            //InputStream context=imageContext;

            System.out.println(image);

          /*  if (image.getName() != null && !image.getName().isEmpty()) {
                String pathImage = getServletContext().getRealPath("/image")
                        + File.separator + image.getName();
                System.out.println("pathImage="+pathImage);
               System.out.println(image);
                try (InputStream in = new FileInputStream(pathImage)) {
                    in.read(image.getContext().readAllBytes());
                }
            } else {
                image.setName("/img1.jpg");
            }*/
        }
        System.out.println("image.getName()" + image.getName());

        //add,edit
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AppConstant.USER_ATTR);

        News news = new News(id_new, user.getId(), title, text, image);
        NewsAction newsAction = EnumManager.getKindNewsAction(action);

        try {
            if (newsDAO.save(news, newsAction)) {
                redirect(response, AppConstant.NEWS_HOME_LIST_CONT);
            } else {
                if (newsAction.equals(NewsAction.ADD)) {
                    forward(request, response, AppConstant.ADD_NEWS_JSP, AppConstant.NEWS_IS_NOT_ADDED_MESSAGE);
                } else if (newsAction.equals(NewsAction.EDIT)) {

                    forward(request, response, AppConstant.NEWS_EDIT_CONT, AppConstant.NEWS_IS_NOT_EDITED_MESSAGE);
                }
            }
        } catch (DAOException e) {
            e.printStackTrace();
            forward(request, response, AppConstant.MY_NEWS_JSP, e.getMessage());
        }
    }
}