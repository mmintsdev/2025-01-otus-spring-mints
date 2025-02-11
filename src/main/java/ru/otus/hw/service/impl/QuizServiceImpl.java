package ru.otus.hw.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.NoQuestionsAvailableException;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.QuizService;
import ru.otus.hw.util.QuestionStringFormatter;

import java.util.List;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    private final QuestionStringFormatter questionStringFormatter;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");

        List<Question> questions = questionDao.findAll();
        if (questions == null || questions.isEmpty()) {
            throw new NoQuestionsAvailableException("No questions available.");
        }

        questions.forEach(question -> ioService.printLine(questionStringFormatter.formatQuestion(question)));
    }
}