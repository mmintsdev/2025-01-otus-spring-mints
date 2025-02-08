package ru.otus.hw.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionStringFormatterTest {

    @Test
    @DisplayName("Тестирование корректного форматирования вопроса с ответами")
    void formatQuestion_ShouldFormatCorrectly_WhenQuestionHasAnswers() {
        // Создаем вопрос с двумя ответами
        Question question = new Question("What is Java?",
                List.of(new Answer("Programming Language", true),
                        new Answer("Coffee", false))
        );

        // Ожидаемый результат форматирования
        String expected = "Q: What is Java?" + System.lineSeparator() +
                "Options: " + System.lineSeparator() +
                "- Programming Language" + System.lineSeparator() +
                "- Coffee" + System.lineSeparator();

        // Получаем фактический результат
        String actual = QuestionStringFormatter.formatQuestion(question);

        // Проверяем, что ожидаемый и фактический результат совпадают
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тестирование корректного поведения при передаче null в качестве вопроса")
    void formatQuestion_ShouldReturnInvalidQuestion_WhenQuestionIsNull() {
        // Ожидаемый результат, когда вопрос равен null
        String expected = "Invalid question";

        // Получаем фактический результат
        String actual = QuestionStringFormatter.formatQuestion(null);

        // Проверяем, что возвращается сообщение об ошибке
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тестирование корректного форматирования вопроса с null ответами")
    void formatQuestion_ShouldHandleNullAnswersInList() {
        // Создаем вопрос с одним null-ответом
        Question question = new Question("What is Java?",
                List.of(new Answer(null, true), new Answer("Coffee", false))
        );

        // Ожидаемый результат с null в одном из ответов
        String expected = "Q: What is Java?" + System.lineSeparator() +
                "Options: " + System.lineSeparator() +
                "- null" + System.lineSeparator() +
                "- Coffee" + System.lineSeparator();

        // Получаем фактический результат
        String actual = QuestionStringFormatter.formatQuestion(question);

        // Проверяем, что ожидаемый и фактический результат совпадают
        assertEquals(expected, actual);
    }
}
