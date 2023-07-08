package by.itclass.controllers.userControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.Image;
import by.itclass.model.beans.User;
import by.itclass.model.exceptions.DAOException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import java.util.List;

@WebServlet(name = "UserImageController", value = AppConstant.USER_IMAGE_CONT)
public class UserImageController extends UserAbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Получние данных пользователя
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AppConstant.USER_ATTR);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = (File) getServletContext().getAttribute(AppConstant.TEMP_DIRECTORY_ATTR);

        //Объект factory будет использовать указанную директорию
        //для хранения своих временных данных(файлов своего формата)
        factory.setRepository(repository);

        //Объект fileUpload обрабатывает request и
        //извлекает из него все переданные данные клиентов
        //это могут быть input-ы любого типа
        //Объект factory используется для работы с переданными файлами
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        //Извлекает все данные из request (извлекаются input-ы любого типа)
        try {
            //Каждый объект FileItem хранит данные из одного input-a
            List<FileItem> items = fileUpload.parseRequest(request);
            for (FileItem item : items) {
                //Метод isFormField() возвращает true, если текущий item
                //хранит данные input-a, тип которого НЕ file
                if (!item.isFormField()) {
                    Image image = new Image(item.getName(), item.getInputStream());
                    user.setImage(image);
                    userDAO.uploadImage(user.getId(), image);
                    //Обработка картики
                    String pathImage = getServletContext().getRealPath("/image") + File.separator + image.getName();
                   // System.out.println("pathImage:" + pathImage);
                    try (OutputStream out = new FileOutputStream(pathImage)) {
                        item.getInputStream().transferTo(out);
                    }
                }
            }
        } catch (FileUploadException | DAOException e) {
            e.printStackTrace();
        }
        forward(request, response, AppConstant.CABINET_JSP);
    }
}
