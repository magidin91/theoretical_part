package collections.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VhoghdenieElementa {
    public Map<Integer, Integer> calculateRepeats(List<Integer> input) {
        Map<Integer, Integer> map = new HashMap<>();
        if (input == null) {
            return map;
        }
        for (Integer element : input) {
            if (map.containsKey(element)) {
                map.put(element, map.get(element) + 1);
            } else {
                map.put(element, 1);
            }
        }
        return map;
    }
}
