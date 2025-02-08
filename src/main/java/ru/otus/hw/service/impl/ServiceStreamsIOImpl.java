package ru.otus.hw.service.impl;

import ru.otus.hw.service.IOService;

import java.io.PrintStream;

public class ServiceStreamsIOImpl implements IOService {
    private final PrintStream printStream;

    public ServiceStreamsIOImpl(PrintStream printStream) {

        this.printStream = printStream;
    }

    @Override
    public void printLine(String s) {
        printStream.println(s);
    }

    @Override
    public void printFormattedLine(String s, Object... args) {
        printStream.printf(s + "%n", args);
    }
}
