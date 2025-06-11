import java.util.*;


public class WordPredictorImpl implements WordPredictor{

    private Map<String, Map<String, Integer>> model = new HashMap<>();

     @Override
    public void train(Scanner scanner) {
        String prevWord = null;

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");

            if (!word.isEmpty()) {
                if (prevWord != null) {
                    model.putIfAbsent(prevWord, new HashMap<>());

                    Map<String, Integer> nextWords = model.get(prevWord);

                    nextWords.put(word, nextWords.getOrDefault(word, 0) + 1);
                }
                prevWord = word;
            }
        }
    }

    @Override
    public String predictNextWord(List<String> context) {
        if (context == null || context.size() == 0) return null;

        String lastWord = context.get(context.size() - 1).toLowerCase();

        Map<String, Integer> nextWords = model.get(lastWord);
        
        if (nextWords == null || nextWords.isEmpty()) return null;

        return Collections.max(nextWords.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
