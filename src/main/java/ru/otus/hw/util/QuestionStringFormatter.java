package ru.otus.hw.util;

import org.springframework.stereotype.Component;
import ru.otus.hw.domain.Question;

import java.util.stream.Collectors;

@Component
public class QuestionStringFormatter {

    private static final String QUESTION_TEMPLATE = "Q: %s" + System.lineSeparator() +
            "Options: %n%s" + System.lineSeparator();

    public String formatQuestion(Question question) {
        if (question == null) {
            return "Invalid question";
        }

        String options = question.answers().stream()
                .map(answer -> "- " + answer.text())
                .collect(Collectors.joining(System.lineSeparator()));

        return String.format(QUESTION_TEMPLATE, question.text(), options);
    }
}