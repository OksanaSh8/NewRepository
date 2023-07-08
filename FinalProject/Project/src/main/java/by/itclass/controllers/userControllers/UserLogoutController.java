package by.itclass.controllers.userControllers;

import by.itclass.constants.AppConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserLogoutController", value = AppConstant.USER_LOGOUT_CONT)
public class UserLogoutController extends UserAbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Удаление сессии
        session.invalidate();
        redirect(response, AppConstant.NEWS_INDEX_CONT);
    }
}
