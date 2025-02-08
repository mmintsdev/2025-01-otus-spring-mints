package ru.otus.hw.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.service.ServiceTestRunner;
import ru.otus.hw.service.ServiceTest;

@RequiredArgsConstructor
public class ServiceTestRunnerImpl implements ServiceTestRunner {

    private final ServiceTest testService;

    @Override
    public void run() {
        testService.executeTest();
    }
}
