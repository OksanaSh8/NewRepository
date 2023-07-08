package by.itclass.constants;

public final class AppConstant {
    public static final String USER_AUTHORIZATION_CONT = "/authorization";
    public static final String USER_LOGOUT_CONT = "/logout";
    public static final String NEWS_HOME_LIST_CONT = "/homeList";
    public static final String SAVE_NEWS_CONT = "/save";
    public static final String NEWS_VIEW_CONT = "/view";
    public static final String NEWS_RATING_CONT = "/update";
    public static final String USER_IMAGE_CONT = "/imageUpload";
    public static final String FILE_UPLOAD_CONT = "/upload";
    public static final String NEWS_EDIT_CONT = "/edit";
    public static final String NEWS_DELETE_CONT = "/delete";
    public static final String NEWS_INDEX_CONT = "/index";
    public static final String USER_REGISTRATION_CONT = "/registration";

    public static final String USER_ATTR = "user";
    public static final String MESSAGE_ATTR = "message";
    public static final String NEWS_LIST_ATTR = "newsList";
    public static final String NEWS_TREESET_ATTR = "newsListRating";
    public static final String NEWS_ATTR = "news";
    public static final String IMAGE_ATTR = "image";
    public static final String PATHIMAGE_ATTR = "pathImage";

    public static final String TEMP_DIRECTORY_ATTR = "javax.servlet.context.tempdir";

    public static final String ID_PARAMETER = "id";
    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String ACTION_PARAMETER = "action";
    public static final String TITLE_PARAMETER = "title";
    public static final String TEXT_PARAMETER = "text";
    public static final String IMAGE_PARAMETER = "image";
    public static final String IMAGE_FILE_PARAMETER = "file";
    public static final String EMAIL_PARAMETER = "email";
    public static final String PASSWORD_VERIFICATION_PARAMETER = "password2";

    public static final String ADD_NEWS_ACTION = "add";
    public static final String EDIT_NEWS_ACTION = "edit";

    public static final String INDEX_JSP = "/index.jsp";
    public static final String AUTH_JSP = "/auth.jsp";
    public static final String MY_NEWS_JSP = "/mynews.jsp";
    public static final String ADD_NEWS_JSP = "/addnews.jsp";
    public static final String EDIT_NEWS_JSP = "/editnews.jsp";
    public static final String NEWS_JSP = "/news.jsp";
    public static final String CABINET_JSP = "/cabinet.jsp";
    public static final String REG_JSP = "/reg.jsp";

    public static final String INCORRECT_LOGIN_OR_PASSWORD_MESSAGE = "Incorrect login or password!";
    public static final String NEWS_IS_NOT_ADDED_MESSAGE = "News is not added";
    public static final String NEWS_IS_NOT_EDITED_MESSAGE = "News is not edited";
    public static final String INCORRECT_PASSWORD_MESSAGE = "The entered passwords do not match";
    public static final String NEWS_RATING_IS_NOT_UPDATE = "News rating is not update. You already voted" +
            "or you are not logged in";

    public static final String JDBC_PROPERTIES = "jdbc.properties";
    public static final String DRIVER_PROPERTY = "driver";
    public static final String URL_PROPERTY = "url";

    public static final String OUTPUT_DATE_PATTERN = "dd.MM.yyyy HH:mm";

    public static final String OUTPUT_DATE_PATTERN1 = "dd.MM.yyyy";

    public static final String IMAGE_NAME_PARAMETER = "imageName";;
    public static final String IMAGE_CONTEXT_PARAMETER = "imageContext";;
}
