package server;

import commons.Question;
import org.junit.jupiter.api.Test;
import server.service.QuestionService;

//import java.io.IOException;
//import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionsImporterTest {
    @Test
    public void importQuestionsBadPathTest() {
        List<String> badPaths = List.of(
                "/activities",
                "activities/",
                "./activities",
                "./activities/"
        );

        QuestionService service = mock(QuestionService.class);
        when(service.addNewQuestion(any(Question.class))).thenReturn(null);

        final QuestionsImporter importer = new QuestionsImporter(service);
        for (final String path : badPaths) assertThrows(IllegalArgumentException.class, () -> importer.importQuestions(path));
    }

    @Test
    public void importQuestionsTest() throws IOException {
        List<Question> collection = new LinkedList<>();

        QuestionService service = mock(QuestionService.class);
        when(service.addNewQuestion(any(Question.class))).thenAnswer(I -> {
            Question q = I.getArgument(0);
            q.id = 0;
            collection.add(q);
            return q;
        });

        final QuestionsImporter importer = new QuestionsImporter(service);

        importer.importQuestions("activity-bank-test");

        List<String> specQuestions = List.of(
                "Using a hairdryer for an hour",
                "Using a leafblower for 15 minutes",
                "Making a hot cup of coffee"
        );

        List<String> specAnswers = List.of("1200", "183", "300");

        String rootPath = "images/activity-bank-test/";
        List<String> specQuestionImages = List.of(
                rootPath + "38/hairdryer.png",
                rootPath + "38/leafblower.png",
                rootPath + "38/coffee.png"
        );

        for (int i = 0; i < 3; i++) {
            assertTrue(specQuestions.get(i).equals(collection.get(i).question) ||
                    ("Instead of '" + specQuestions.get(i) + "', you could:").equals(collection.get(i).question) ||
                    ("What requires more energy?").equals(collection.get(i).question));
            assertTrue(specAnswers.get(i).equals(collection.get(i).answer) ||
                    "Using a hairdryer for an hour".equals(collection.get(i).answer));
            assertEquals(specQuestionImages.get(i), collection.get(i).questionImage);
            assertNotNull(collection.get(i).wrongAnswer1);
            assertNotNull(collection.get(i).wrongAnswer2);
        }
    }
}