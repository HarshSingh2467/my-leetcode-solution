import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Map the knowledge base for O(1) lookups
        Map<String, String> map = new HashMap<>(knowledge.size());
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int n = s.length();
        
        // Step 2: Parse the string in one pass
        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                // Find the closing bracket
                int j = s.indexOf(')', i + 1);
                // Extract the key inside the brackets
                String key = s.substring(i + 1, j);
                // Append the value from the map or "?" if missing
                sb.append(map.getOrDefault(key, "?"));
                // Move the pointer past the closing bracket
                i = j + 1;
            } else {
                // Append regular characters
                sb.append(c);
                i++;
            }
        }
        
        return sb.toString();
    }
}
