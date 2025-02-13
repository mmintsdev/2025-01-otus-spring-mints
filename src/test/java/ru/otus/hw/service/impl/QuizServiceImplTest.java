package ru.otus.hw.service.impl;

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
import ru.otus.hw.service.IOService;
import ru.otus.hw.util.QuestionStringFormatter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

    @Mock
    private IOService ioService;

    @Mock
    private QuestionDao questionDao;

    @Mock
    private QuestionStringFormatter formatter;

    @InjectMocks
    private QuizServiceImpl serviceTest;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Должен вывести вопросы, если они есть в базе данных")
    void executeTest_ShouldPrintQuestions_WhenQuestionsAvailable() {

        Question question = new Question("What is Java?", List.of(new Answer("Programming Language", true)));
        when(questionDao.findAll()).thenReturn(List.of(question));

        serviceTest.executeTest();

        verify(ioService).printFormattedLine("Please answer the questions below%n");
        verify(ioService).printLine(formatter.formatQuestion(question));
    }

    @Test
    @DisplayName("Должен выбросить исключение, если вопросов нет в базе данных")
    void executeTest_ShouldThrowException_WhenNoQuestionsAvailable() {

        when(questionDao.findAll()).thenReturn(List.of());

        assertThrows(NoQuestionsAvailableException.class, () -> serviceTest.executeTest());
    }

    @Test
    @DisplayName("Должен выбросить исключение, если вопросы равны null")
    void executeTest_ShouldThrowException_WhenQuestionsAreNull() {

        when(questionDao.findAll()).thenReturn(null);

        assertThrows(NoQuestionsAvailableException.class, () -> serviceTest.executeTest());
    }

    @Test
    @DisplayName("Сервис должен корректно инициализироваться")
    void testService_ShouldInitializeCorrectly() {
        assertNotNull(serviceTest);
    }
}