package by.itclass.model.db;

public final class SQLRequest {
    public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD =
            "select us.id as id_user," +
                    "us.login," +
                    "us.email," +
                    "im.id as id_image," +
                    "im.name," +
                    "im.context " +
                    "from users as us " +
                    "left join users_image as im on us.id=im.id_user " +
                    "where us.login=? and us.password=?";

    public static final String SELECT_NEWS_LIST_BY_ID_USER =
            "select n.id," +
                    "n.title," +
                    "n.text," +
                    "n.date," +
                    "im.name," +
                    "im.context " +
                    "from news as n " +
                    "left join news_image as im on n.id=im.id_news " +
                    "where n.id_user=?";

    public static final String SELECT_NEWS_BY_ID =
            "select n.id_user," +
                    "n.title," +
                    "n.text," +
                    "n.date," +
                    "n.rating," +
                    "im.name," +
                    "im.context " +
                    "from news as n " +
                    "left join news_image as im on n.id=im.id_news " +
            "where n.id=?";

    public static final String INSERT_NEWS =
            "insert news(title,text,date, id_user) value(?,?,?,?)";

    public static final String UPDATE_NEWS_BY_ID =
            "update news set title=?, text=?, date=? where id_user=? and id=?";

    public static final String UPDATE_NEWS_RATING_BY_ID =
            "update news set rating=(rating + ?) where id=?";

    public static final String INSERT_IMAGE_FOR_USER =
            "insert users_image(id_user, name, context) value(?,?,?)";

                        //UPLOAD_NEWS_IMAGE ...
    public static final String INSERT_IMAGE_FOR_NEWS =
            "insert news_image(id_news, name, context) value(?,?,?)";

    public static final String UPDATE_IMAGE_FOR_NEWS =
            "update news_image set  name=?, context=? where id_news=?";

    public static final String DELETE_NEWS_BY_ID  =
            "DELETE FROM news WHERE id=?";

    public static final String SELECT_NEWS_LIST_BY_DATE =
            "select n.id, " +
                    "n.title, " +
                    "cast(text as CHAR(150)) as text, " +
                    "n.date, " +
                    "n.rating, " +
                    "im.name, " +
                    "im.context " +
                    "from news as n " +
                    "left join news_image as im on n.id=im.id_news " +
                    "where DATEDIFF(current_timestamp, n.date)<7 " +
                    "order by n.date desc";



    public static final String SELECT2_USER_BY_LOGIN_AND_PASSWORD =
            "select * from users where login=? and password=?";

    public static final String INSERT_RATING =
            "insert into rating(id_user, id_news) values (?, ?)" ;

    //Определение неизменных значений полей SQL запроса
    public static final int UPP_RATING_VALUE = 1;
    public static final int DOWN_RATING_VALUE = -1;
    public static final int OTHER_RATING_VALUE = 0;

    //Значения констант должны соответствовать именам столбцов в БД
    public static final String ID_COLUMN = "id";
    public static final String ID_USER_COLUMN = "id_user";
    public static final String EMAIL_COLUMN = "email";
    public static final String TITLE_COLUMN = "title";
    public static final String TEXT_COLUMN = "text";
    public static final String RATING_COLUMN = "rating";
    public static final String ID_IMAGE_COLUMN = "id_image";
    public static final String NAME_COLUMN = "name";
    public static final String CONTEXT_COLUMN = "context";
    public static final String DATE_COLUMN = "date";



}
