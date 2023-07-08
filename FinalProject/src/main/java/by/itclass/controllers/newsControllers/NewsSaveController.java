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
import java.io.IOException;


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

        Image image = (Image) request.getAttribute(AppConstant.IMAGE_ATTR);



        //add,edit
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AppConstant.USER_ATTR);

        News news = new News(id_new, user.getId(), title, text, image);
        NewsAction newsAction = EnumManager.getKindNewsAction(action);

        try {
            if (newsDAO.save(news, newsAction)) {
                System.out.println("проверка");
                redirect(response, AppConstant.NEWS_HOME_LIST_CONT);
            } else {
                if (newsAction.equals(NewsAction.ADD)) {
                    forward(request, response, AppConstant.ADD_NEWS_JSP, AppConstant.NEWS_IS_NOT_ADDED_MESSAGE);
            } else if (newsAction.equals(NewsAction.EDIT)) {
                    System.out.println("переход с save cont в edit");
                    forward(request, response, AppConstant.NEWS_EDIT_CONT, AppConstant.NEWS_IS_NOT_EDITED_MESSAGE);
                }}
        }  catch (DAOException e) {
            e.printStackTrace();
            forward(request, response, AppConstant.MY_NEWS_JSP, e.getMessage());
        }
    }
}