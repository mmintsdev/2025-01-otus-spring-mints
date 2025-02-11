package ru.otus.hw.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionStringFormatterTest {

    private final QuestionStringFormatter formatter = new QuestionStringFormatter();

    @Test
    @DisplayName("Тестирование корректного форматирования вопроса с ответами")
    void formatQuestion_ShouldFormatCorrectly_WhenQuestionHasAnswers() {
        Question question = new Question("What is Java?",
                List.of(new Answer("Programming Language", true),
                        new Answer("Coffee", false))
        );

        String expected = "Q: What is Java?" + System.lineSeparator() +
                "Options: " + System.lineSeparator() +
                "- Programming Language" + System.lineSeparator() +
                "- Coffee" + System.lineSeparator();

        String actual = formatter.formatQuestion(question);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тестирование корректного поведения при передаче null в качестве вопроса")
    void formatQuestion_ShouldReturnInvalidQuestion_WhenQuestionIsNull() {
        String expected = "Invalid question";

        String actual = formatter.formatQuestion(null);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тестирование корректного форматирования вопроса с null ответами")
    void formatQuestion_ShouldHandleNullAnswersInList() {
        Question question = new Question("What is Java?",
                List.of(new Answer(null, true), new Answer("Coffee", false))
        );

        String expected = "Q: What is Java?" + System.lineSeparator() +
                "Options: " + System.lineSeparator() +
                "- null" + System.lineSeparator() +
                "- Coffee" + System.lineSeparator();

        String actual = formatter.formatQuestion(question);

        assertEquals(expected, actual);
    }
}
