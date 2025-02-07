package ru.otus.hw.service;

public interface StreamsIOService {
    void printLine(String s);

    void printFormattedLine(String s, Object ...args);
}
