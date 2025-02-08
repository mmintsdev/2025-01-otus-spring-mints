package ru.otus.hw;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw.service.ServiceTestRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ApplicationTest {

    @Test
    void contextShouldLoadSuccessfully() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ServiceTestRunner testRunnerService = context.getBean(ServiceTestRunner.class);

        assertNotNull(testRunnerService, "TestRunnerService должен быть загружен в контексте");
    }

    @Test
    void testRunnerServiceRunShouldBeCalled() {
        ServiceTestRunner testRunnerService = mock(ServiceTestRunner.class);

        doNothing().when(testRunnerService).run();

        ApplicationContext context = mock(ApplicationContext.class);
        when(context.getBean(ServiceTestRunner.class)).thenReturn(testRunnerService);

        var retrievedService = context.getBean(ServiceTestRunner.class);
        retrievedService.run();

        verify(testRunnerService, times(1)).run();
    }
}
