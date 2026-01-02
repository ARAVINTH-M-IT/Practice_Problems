import java.util.*;

public class Q3{
    public static List<Integer> findIndices(String text, String query, int k) {
        String[] words = text.split("\\s+");
        String[] qWords = query.split("\\s+");

        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.computeIfAbsent(words[i], x -> new ArrayList<>()).add(i);
        }

        List<Integer> result = new ArrayList<>();
        if (!map.containsKey(qWords[0])) return result;

        for (int start : map.get(qWords[0])) {
            boolean valid = true;
            int prev = start;
            for (int i = 1; i < qWords.length; i++) {
                if (!map.containsKey(qWords[i])) {
                    valid = false;
                    break;
                }
                List<Integer> list = map.get(qWords[i]);
                int idx = Collections.binarySearch(list, prev + 1);
                if (idx < 0) idx = -idx - 1;
                if (idx == list.size() || list.get(idx) - start > k) {
                    valid = false;
                    break;
                }
                prev = list.get(idx);
            }
            if (valid) result.add(start);
        }

        return result;
    }

    public static void main(String[] args) {
        String text = "The quick brown fox is quick something quick fox";
        String query = "quick fox";
        int k = 2;
        System.out.println(findIndices(text, query, k));
    }
}