package search;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class InvertedIndex {
    private static LinkedHashMap<String, ArrayList<Integer>> index;

    public InvertedIndex(ArrayList<String> peopleData) {
        index = new LinkedHashMap<>();

        for (int i = 0; i < peopleData.size(); i++) {
            String[] words = peopleData.get(i).split(" ");

            for (String word : words) {
                ArrayList<Integer> list = index.get(word);

                if (list==null) {
                    list = new ArrayList<>();
                }

                list.add(i);
                index.put(word, list);
            }
        }
    }

    public LinkedHashMap<String, ArrayList<Integer>> getIndex() {
        return index;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, ArrayList<Integer>> entry: index.entrySet()) {
            builder.append(String.format("%s -> %s\n",entry.getKey(),entry.getValue().toString()));
        }

        return builder.toString();
    }
}
