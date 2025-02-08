package ru.otus.hw.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.service.IOService;
import ru.otus.hw.util.QuestionStringFormatter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TestServiceImplTest {

    private IOService ioService;
    private QuestionDao questionDao;
    private ServiceTestImpl testService;

    @BeforeEach
    void setUp() {
        ioService = mock(IOService.class);
        questionDao = mock(QuestionDao.class);
        testService = new ServiceTestImpl(ioService, questionDao);
    }

    @Test
    @DisplayName("Тестирование: корректная печать вопросов, если они доступны")
    void executeTest_ShouldPrintQuestions_WhenQuestionsAvailable() {
        List<Question> questions = List.of(new Question("What is Java?", List.of(new Answer("Programming Language", true))));

        when(questionDao.findAll()).thenReturn(questions);

        testService.executeTest();

        var inOrder = inOrder(ioService);

        inOrder.verify(ioService).printLine(""); // Ожидаемый вызов пустой строки
        inOrder.verify(ioService).printFormattedLine("Please answer the questions below%n");
        inOrder.verify(ioService).printLine(QuestionStringFormatter.formatQuestion(questions.get(0)));
    }

    @Test
    @DisplayName("Тестирование: сообщение о отсутствии вопросов, если вопросов нет")
    void executeTest_ShouldPrintNoQuestionsMessage_WhenNoQuestionsAvailable() {
        when(questionDao.findAll()).thenReturn(List.of());

        testService.executeTest();

        verify(ioService).printLine("No questions available.");
    }

    @Test
    @DisplayName("Тестирование: обработка null списка вопросов")
    void executeTest_ShouldHandleNullQuestionsList() {
        when(questionDao.findAll()).thenReturn(null);

        testService.executeTest();

        verify(ioService).printLine("No questions available.");
    }

    @Test
    @DisplayName("Тестирование: проверка правильной инициализации сервиса")
    void testService_ShouldInitializeCorrectly() {
        assertNotNull(testService);
    }

}
