package ru.otus.hw.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.service.QuizService;
import ru.otus.hw.service.ServiceQuizRunner;

@RequiredArgsConstructor
public class ServiceQuizRunnerImpl implements ServiceQuizRunner {

    private final QuizService quizService;

    @Override
    public void run() {
        quizService.executeTest();
    }
}
