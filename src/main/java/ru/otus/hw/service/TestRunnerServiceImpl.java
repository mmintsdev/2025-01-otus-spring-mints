package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.hw.exceptions.NoQuestionsAvailableException;
import ru.otus.hw.exceptions.QuestionReadException;

@Slf4j
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
        log.error("No questions available to execute the test.", e);
        System.out.println("Error: No test questions available.");
    }

    private void handleQuestionReadException(QuestionReadException e) {
        log.error("Error occurred while reading questions from the data source.", e);
        System.out.println("Error when reading questions from the data source.");
    }

    private void handleNullPointerException(NullPointerException e) {
        log.error("A NullPointerException occurred. This might indicate a bug in the code.", e);
        System.out.println("Error: Unexpected error occurred (NullPointerException). Please contact support.");
    }

    private void handleUnexpectedException(Exception e) {
        log.error("An unexpected error occurred while executing the test.", e);
        System.out.println("There was an unexpected error: " + e.getMessage());
    }
}
