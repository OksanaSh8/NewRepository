package by.itclass.controllers.userControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.Image;
import by.itclass.model.beans.User;
import by.itclass.model.exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "UserAuthorizationController", value = AppConstant.USER_AUTHORIZATION_CONT)
public class UserAuthorizationController extends UserAbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(AppConstant.LOGIN_PARAMETER);
        String password = request.getParameter(AppConstant.PASSWORD_PARAMETER);

        //user, null
        try {
            User user = userDAO.get(login, password);
            Image image = user.getImage();
            if (user.getImage().getName() != null && !user.getImage().getName().isEmpty()) {
                //Обработка картинки

                String pathImage = getServletContext().getRealPath("/image")
                        + File.separator + image.getName();
                try (OutputStream out = new FileOutputStream(pathImage)) {
                    out.write(image.getContext().readAllBytes());
                }
            } else {
                image.setName("/img1.jpg");
            }
            //

            HttpSession session = request.getSession();
            //Метод setMaxInactiveInterval() время обновления(жизни) сессии
            //значение указывается в секундах
            //session.setMaxInactiveInterval(30);
            //Метод isNew() возращает true для сервером была создана
            //новая сессия в рамках текущего запроса, иначе false
            //System.out.println("isNew(): " + session.isNew());
            //Добавление аттрибута в сессию
            session.setAttribute(AppConstant.USER_ATTR, user);
            //Получение значения аттрибута из сессии
            //User user1 = (User) session.getAttribute(AppConstant.USER_ATTR);
            //request.setAttribute(AppConstant.USER_ATTR, user);
            redirect(response, AppConstant.NEWS_INDEX_CONT);
        } catch (DAOException e) {
            forward(request, response, AppConstant.AUTH_JSP, e.getMessage());
            e.printStackTrace();
        }
    }
}
