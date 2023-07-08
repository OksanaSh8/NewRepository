package by.itclass.model.memory;

import by.itclass.model.beans.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserMemory {
    private static Map<User, String> usersMap;

    static {
        usersMap = new HashMap<>();
        usersMap.put(new User(1, "admin", "admin@gmail.com"), "q123");
    }

    public static User selectByLoginAndPassword(String login, String password) {
        Set<User> users = usersMap.keySet();
        User user = null;
        for (User us : users) {
            if (us.getLogin().equals(login)) {
                String pass = usersMap.get(us);
                if (pass.equals(password)) {
                    user = us;
                }
            }
        }
        return user;
    }
}
