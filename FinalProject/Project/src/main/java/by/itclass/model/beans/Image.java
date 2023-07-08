package by.itclass.model.beans;

import java.io.InputStream;

public class Image {
    private int id;
    private String name;
    private InputStream context;

    public Image() {
    }

    public Image(String name, InputStream context) {
        this.name = name;
        this.context = context;
    }

    public Image(int id, String name, InputStream context) {
        this.id = id;
        this.name = name;
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", context=" + context +
                '}';
    }
}
