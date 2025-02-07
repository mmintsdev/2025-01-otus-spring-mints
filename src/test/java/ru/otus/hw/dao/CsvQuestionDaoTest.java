package ru.otus.hw.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CsvQuestionDaoTest {

    @Mock
    private TestFileNameProvider fileNameProvider;

    @InjectMocks
    private CsvQuestionDao csvQuestionDao;

    @BeforeEach
    void setUp() {
        when(fileNameProvider.getTestFileName()).thenReturn("test-questions.csv");
    }

    @Test
    void testFindAll_Success() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream mockStream = classLoader.getResourceAsStream("test-questions.csv");
        assertNotNull(mockStream, "Test CSV file is missing");

        List<Question> questions = csvQuestionDao.findAll();
        assertFalse(questions.isEmpty(), "The list of questions is empty");
    }

    @Test
    void testFindAll_FileNotFound() {
        when(fileNameProvider.getTestFileName()).thenReturn("non-existent.csv");

        QuestionReadException ex = assertThrows(QuestionReadException.class, csvQuestionDao::findAll);
        assertTrue(ex.getMessage().contains("File not found"));
    }
}
