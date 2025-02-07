package ru.otus.hw.util;

import org.junit.jupiter.api.Test;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.util.QuestionStringFormatter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionStringFormatterTest {

    @Test
    void formatQuestion_ShouldFormatCorrectly_WhenQuestionHasAnswers() {
        Question question = new Question("What is Java?",
                List.of(new Answer("Programming Language", true),
                        new Answer("Coffee", false))
        );

        String expected = "Q: What is Java?\nOptions: - Programming Language\n- Coffee\n";
        String actual = QuestionStringFormatter.formatQuestion(question);

        assertEquals(expected, actual);
    }

    @Test
    void formatQuestion_ShouldHandleEmptyAnswersList() {
        Question question = new Question("What is Java?", List.of());

        String expected = "Q: What is Java?\nOptions: \n";
        String actual = QuestionStringFormatter.formatQuestion(question);

        assertEquals(expected, actual);
    }

    @Test
    void formatQuestion_ShouldReturnInvalidQuestion_WhenQuestionIsNull() {
        String expected = "Invalid question";
        String actual = QuestionStringFormatter.formatQuestion(null);

        assertEquals(expected, actual);
    }
}
