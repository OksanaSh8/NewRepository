package by.itclass.model.beans;

public class User {
    private int id;
    private String login;
    private String email;
    private Image image;

    public User() {
    }

    public User(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public User(int id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public User(int id, String login, String email, Image image) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "by.itclass.model.beans.User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", image=" + image +
                '}';
    }
}
