package ru.otus.hw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.NoQuestionsAvailableException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @Mock
    private IOService ioService;

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private TestServiceImpl testService;

    private Question question;

    @BeforeEach
    void setUp() {
        // Инициализация тестовых данных
        Answer answer1 = new Answer("Programming Language", true);
        Answer answer2 = new Answer("Coffee", false);
        question = new Question("What is Java?", List.of(answer1, answer2));
    }

    @Test
    @DisplayName("Тестирование успешного выполнения, когда вопросы есть")
    void executeTest_ShouldPrintQuestions_WhenQuestionsAvailable() {
        when(questionDao.findAll()).thenReturn(List.of(question));

        testService.executeTest();

        verify(ioService).printFormattedLine("Please answer the questions below%n");
        verify(ioService).printFormattedLine("Q: %s", "What is Java?");
        verify(ioService).printFormattedLine("- %s", "Programming Language");
        verify(ioService).printFormattedLine("- %s", "Coffee");
    }

    @Test
    @DisplayName("Тестирование выбрасывания исключения, когда нет вопросов")
    void executeTest_ShouldThrowException_WhenNoQuestionsAvailable() {
        when(questionDao.findAll()).thenReturn(List.of());

        assertThrows(NoQuestionsAvailableException.class, () -> testService.executeTest());
    }

    @Test
    @DisplayName("Тестирование выбрасывания исключения, когда список вопросов равен null")
    void executeTest_ShouldThrowException_WhenQuestionsAreNull() {
        when(questionDao.findAll()).thenReturn(null);

        assertThrows(NoQuestionsAvailableException.class, () -> testService.executeTest());
    }

    @Test
    @DisplayName("Тестирование корректного выполнения, когда вопрос не имеет ответов")
    void executeTest_ShouldHandleQuestionWithoutAnswers() {

        Question questionWithoutAnswers = new Question("What is Java?", List.of());
        when(questionDao.findAll()).thenReturn(List.of(questionWithoutAnswers));

        testService.executeTest();

        verify(ioService).printFormattedLine("Please answer the questions below%n");
        verify(ioService).printFormattedLine("Q: %s", "What is Java?");
        verify(ioService, never()).printFormattedLine("- %s", "Programming Language");
    }
}
