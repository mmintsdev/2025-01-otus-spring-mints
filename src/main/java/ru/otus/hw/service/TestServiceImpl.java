package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.NoQuestionsAvailableException;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");

        List<Question> questions = questionDao.findAll();

        if (questions == null || questions.isEmpty()) {
            throw new NoQuestionsAvailableException("No questions available.");
        }

        for (Question question : questions) {
            ioService.printFormattedLine("Q: %s", question.text());
            question.answers().forEach(answer -> ioService.printFormattedLine("- %s", answer.text()));
        }
    }
}