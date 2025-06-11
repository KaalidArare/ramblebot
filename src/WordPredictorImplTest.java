import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class WordPredictorImplTest {
    
    @Test
    public void testPredictionAfterTraining() {
        String text = "I like pizza. I like pasta. I like coding.";
        Scanner scanner = new Scanner(text);

        WordPredictorImpl predictor = new WordPredictorImpl();
        predictor.train(scanner);

        List<String> context = Arrays.asList("I", "like");
        String prediction = predictor.predictNextWord(context);

        assertNotNull(prediction);
        assertTrue(List.of("pizza", "pasta", "coding").contains(prediction));
    }

    @Test
    public void testPredictionQuestion() {
        String text = "what is crouching? what is happening? What is that?";
        Scanner scanner = new Scanner(text);

        WordPredictorImpl predictor = new WordPredictorImpl();
        predictor.train(scanner);

        List<String> context = Arrays.asList("what", "is");
        String prediction = predictor.predictNextWord(context);

        assertNotNull(prediction);
        assertTrue(List.of("crouching", "happen", "that").contains(prediction));
    }

    @Test
    public void testPredictionNull() {
        WordPredictorImpl predictor = new WordPredictorImpl();
        predictor.train(new Scanner("")); 

        List<String> context = List.of();
        String prediction = predictor.predictNextWord(context);

        assertNull(prediction);
    }
}
