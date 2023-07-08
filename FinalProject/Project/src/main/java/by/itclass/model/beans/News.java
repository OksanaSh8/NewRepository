package by.itclass.model.beans;

import by.itclass.constants.AppConstant;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;


public class News implements Comparable<News>{
    private static SimpleDateFormat dateFormat=
            new SimpleDateFormat(AppConstant.OUTPUT_DATE_PATTERN);

    private static SimpleDateFormat dateFormat1=
            new SimpleDateFormat(AppConstant.OUTPUT_DATE_PATTERN1);

    private int id;
    private int idUser;
    private String title;
    private String text;
    private int rating;
    private Image image;
    private String date;

    public News() {
    }

    public News(int idUser, String title, String text) {
        this.idUser = idUser;
        this.title = title;
        this.text = text;
    }

    public News(int id, int idUser, String title, String text) {
        this.id = id;
        this.idUser = idUser;
        this.title = title;
        this.text = text;
    }

    public News(int id, int idUser, String title, String text, Image image, String date) {
        this.id = id;
        this.idUser = idUser;
        this.title = title;
        this.text = text;
        this.image = image;
        this.date=date; //01.01.1970
    }

    public News(int id, int idUser, String title, String text, Image image, String date, int rating) {
        this.id = id;
        this.idUser = idUser;
        this.title = title;
        this.text = text;
        this.image = image;
        this.date=date; //01.01.1970
        this.rating = rating;
    }



    public News(int id, int idUser, String title, String text, int rating) {
        this.id = id;
        this.idUser = idUser;
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public News(int id, int idUser, String title, String text, Image image) {
        this.id = id;
        this.idUser = idUser;
        this.title = title;
        this.text = text;
        this.image = image;
    }

    public News(int id, String title, String text, Image image, String date, int rating) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.image = image;
        this.date=date; //01.01.1970
        this.rating = rating;
    }



    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public Image getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }



    public String getStringDate() {

        return dateFormat.format(date);
    }

    public  String getStringDate1() {

        return dateFormat1.format(date);
    }

    @Override
    public int compareTo(News news) {
        return news.getRating()-rating;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", image=" + image +
                ", date='" + date + '\'' +
                '}';
    }
}
