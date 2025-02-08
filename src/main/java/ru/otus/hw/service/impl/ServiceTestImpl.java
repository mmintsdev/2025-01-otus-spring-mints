package ru.otus.hw.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;
import ru.otus.hw.service.StreamsIOService;
import ru.otus.hw.service.ServiceTest;
import ru.otus.hw.util.QuestionStringFormatter;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ServiceTestImpl implements ServiceTest {

    private final StreamsIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");

        List<Question> questions = Optional.ofNullable(questionDao.findAll()).filter(q -> !q.isEmpty()).orElseGet(() -> {
            ioService.printLine("No questions available.");
            return List.of();
        });

        questions.forEach(question -> ioService.printLine(QuestionStringFormatter.formatQuestion(question)));
    }
}
