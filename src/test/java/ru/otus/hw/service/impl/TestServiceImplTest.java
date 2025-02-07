package ru.otus.hw.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.service.StreamsIOService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private StreamsIOService ioService;

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private TestServiceImpl testService;

    @Test
    void executeTest_ShouldWorkWithoutExceptions() {
        // Подготовка мока с вопросами
        List<Question> mockQuestions = List.of(
                new Question("What is Java?", List.of(new Answer("Programming Language", true))),
                new Question("What is Spring?", List.of(new Answer("Framework", true)))
        );
        when(questionDao.findAll()).thenReturn(mockQuestions);

        // Проверка, что метод выполняется без исключений
        assertDoesNotThrow(() -> testService.executeTest());

        // Проверка вызовов методов
        verify(questionDao, times(1)).findAll();
        verify(ioService, atLeastOnce()).printLine(anyString());
    }
}
