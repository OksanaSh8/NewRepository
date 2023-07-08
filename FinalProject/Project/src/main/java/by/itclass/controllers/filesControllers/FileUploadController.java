package by.itclass.controllers.filesControllers;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.AbstractController;
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

@WebServlet(name = "FileUploadController", value = AppConstant.FILE_UPLOAD_CONT)
public class FileUploadController extends AbstractController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = (File) getServletContext().getAttribute(AppConstant.TEMP_DIRECTORY_ATTR);
        factory.setRepository(repository);

        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        try {
            List<FileItem> items = fileUpload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    Image image = new Image(item.getName(), item.getInputStream());
                    System.out.println(image);
                    request.setAttribute(AppConstant.IMAGE_ATTR, image);
                } else {
                    request.setAttribute(item.getFieldName(), item.getString());
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        //if(...)
        forward(request, response, AppConstant.SAVE_NEWS_CONT);
    }
}
