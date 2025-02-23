package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");

        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        questions.forEach(question -> {
            ioService.printLine(question.text());
            var answersList = question.answers();
            outputPossibleAnswers(answersList);

            var chosenAnswerIndex = getChosenAnswerIndex(answersList);
            boolean isAnswerValid = answersList.get(chosenAnswerIndex).isCorrect();

            ioService.printLine(isAnswerValid ? "Correctly!\n" : "Wrong!\n");
            testResult.applyAnswer(question, isAnswerValid);
        });

        return testResult;
    }

    private void outputPossibleAnswers(List<Answer> answersList) {
        answersList.stream()
                .map(answer -> answersList.indexOf(answer) + 1 + ". " + answer.text())
                .forEach(ioService::printLine);
    }

    private int getChosenAnswerIndex(List<Answer> answersList) {
        ioService.printLine("Enter the response number: ");
        int chosenAnswerIndex = ioService.readIntForRange(1, answersList.size(), "Incorrect input!") - 1;
        return chosenAnswerIndex;
    }
}
