package all;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserAndMail {
    public static void main(String[] args) {
        Map<String, List<String>> userAndMails = new ConcurrentHashMap<>();

        userAndMails.put("user1", new ArrayList<> (List.of("mail1", "mail2")));
        userAndMails.put("user2", new ArrayList<> (List.of("mail2", "mail3")));
        userAndMails.put("user3", new ArrayList<> (List.of("mail4", "mail5")));

        Set<Map.Entry<String, List<String>>> entries = userAndMails.entrySet();
        Collection<List<String>> values = userAndMails.values();

        for (Map.Entry<String, List<String>> entry : entries) {
            List<String> ourValue = entry.getValue();
            for (List<String> value : values) {
                for (String mail : value) {
                    if (ourValue.contains(mail)) {
//                       value.addAll(ourValue);
//                       userAndMails.remove(entry.getKey());
                    }
                }
            }
        }

        System.out.println(userAndMails);
    }
}
