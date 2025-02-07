package ru.otus.hw.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.service.StreamsIOService;
import ru.otus.hw.service.TestService;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final StreamsIOService ioService;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        // Получить вопросы из дао и вывести их с вариантами ответов
    }
}
