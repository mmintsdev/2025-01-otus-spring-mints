package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.exceptions.NoQuestionsAvailableException;
import ru.otus.hw.exceptions.QuestionReadException;

@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {

    private final TestService testService;

    @Override
    public void run() {
        try {
            testService.executeTest();
        } catch (NoQuestionsAvailableException e) {
            handleNoQuestionsAvailableException(e);
        } catch (QuestionReadException e) {
            handleQuestionReadException(e);
        } catch (NullPointerException e) {
            handleNullPointerException(e);
        } catch (Exception e) {
            handleUnexpectedException(e);
        }
    }

    private void handleNoQuestionsAvailableException(NoQuestionsAvailableException e) {
        System.out.println("Error: No test questions available.");
    }

    private void handleQuestionReadException(QuestionReadException e) {
        System.out.println("Error when reading questions from the data source.");
    }

    private void handleNullPointerException(NullPointerException e) {
        System.out.println("Error: Unexpected error occurred (NullPointerException). Please contact support.");
    }

    private void handleUnexpectedException(Exception e) {
        System.out.println("Error: No test questions available.");
    }
}
