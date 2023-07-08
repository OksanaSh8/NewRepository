package by.itclass.controllers.userControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.User;
import by.itclass.model.exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserRegistrationController", value = AppConstant.USER_REGISTRATION_CONT)
public class UserRegistrationController extends UserAbstractController {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(AppConstant.LOGIN_PARAMETER);

        String password = request.getParameter(AppConstant.PASSWORD_PARAMETER);
        String password2 = request.getParameter(AppConstant.PASSWORD_VERIFICATION_PARAMETER);
        String email = request.getParameter(AppConstant.EMAIL_PARAMETER);

        User user = new User(login, email);

        if (password.equals(password2)==false){
            forward(request, response, AppConstant.REG_JSP, AppConstant.INCORRECT_PASSWORD_MESSAGE);
        }

        try {
            userDAO.add(user, password);
            redirect(response, AppConstant.AUTH_JSP);
        }  catch (DAOException | NumberFormatException e) {
        e.printStackTrace();
            forward(request, response, AppConstant.REG_JSP, AppConstant.INCORRECT_LOGIN_OR_PASSWORD_MESSAGE);
        }

    }
}

