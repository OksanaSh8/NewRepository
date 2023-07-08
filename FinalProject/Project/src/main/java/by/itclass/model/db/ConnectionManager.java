package by.itclass.model.db;

import by.itclass.constants.AppConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    //Все настройки перенесены в файл jdbc.properties в папке resources
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/itclass_po56_58?serverTimezone=Europe/Minsk";
//    private static final String DB_USER = "root";
//    private static final String DB_PASS = "";
//    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    //Поле хранит активное соединение с БД
    private static Connection cn;
    private static Properties prop;

    static {
        prop = PropertiesManager.load(AppConstant.JDBC_PROPERTIES);

        try {
            Class.forName(prop.getProperty(AppConstant.DRIVER_PROPERTY));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        //Метод isClosed() возвращает true, если созданное ранее
        //соед. уже закрыто, иначе false
        if (cn == null || cn.isClosed()) {
            cn = DriverManager.getConnection(prop.getProperty(AppConstant.URL_PROPERTY), prop);
        }
        return cn;
    }
}
