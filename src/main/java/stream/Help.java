package stream;

import java.util.HashMap;
import java.util.Map;

public class Help {
    public static void main(String[] args) {
        Map<User, String> users = new HashMap<>();
        users.put(new User(), "user1");
        users.put(new User(), "user1");
        User find = users.keySet().stream().findFirst().orElse(null);
        System.out.println(find);
    }

}

class User {
    @Override
    public String toString() {
        return "User{}";
    }
}
