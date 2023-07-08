package by.itclass.model.memory;

import by.itclass.model.beans.News;

import java.util.ArrayList;
import java.util.List;

public class NewsMemory {
    private static List<News> newsList;

    static {
        newsList = new ArrayList<>();
        newsList.add(new News(1, 1, "Title1", "Text1"));
        newsList.add(new News(2, 1, "Title2", "Text2"));
        newsList.add(new News(3, 1, "Title3", "Text3"));
    }

    public static List<News> selectByIdUser(Integer idUser) {
        List<News> list = new ArrayList<>();
        for (News news : newsList) {
            if (news.getIdUser() == idUser) {
                list.add(news);
            }
        }
        return list;
    }

    public static boolean insert(News news) {
        int id = 0;
        for (News currentNews : newsList) {
            if (currentNews.getIdUser() == news.getIdUser()) {
                id = Math.max(currentNews.getId(), id);
            }
        }
        news.setId(++id);
        return newsList.add(news);
    }

    public static boolean update(News news) {
        return false;
    }
}
