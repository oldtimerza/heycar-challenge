package com.heycar.heycarchallenge.logging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogBuilderTest {
    @Mock
    private Logger logger;

    @InjectMocks
    private LogBuilder logBuilder;

    @Test
    public void info_whenGivenLoggingInfo_shouldCallLoggingInfo() {
        Date date = new Date(2013, 12, 01, 8,15, 00);
        String method = "updateListings";

        logBuilder
                .withLogger(logger)
                .withMarker(Markers.METHOD_START())
                .withDate(date)
                .withMethod(method)
                .withParameter("someParam", 1)
                .withParameter("someList", Arrays.asList("Test"))
                .info();

        String expectedMessage = "Thursday 01 January 3914 08:15:00.000+0200 method: updateListings parameters: someParam 1,someList [Test], ";
        verify(logger).info(eq(Markers.METHOD_START()), eq(expectedMessage));
    }


    @Test
    public void error_whenGivenErrorInfo_shouldCallLoggingErrorWithException() {
        Date date = new Date(2013, 12, 01, 8,15, 00);
        String method = "updateListings";
        Exception exception = new Exception("Something went wrong");

        logBuilder
                .withLogger(logger)
                .withMarker(Markers.METHOD_START())
                .withDate(date)
                .withMethod(method)
                .withParameter("someParam", 1)
                .withParameter("someList", Arrays.asList("Test"))
                .withError(exception)
                .error();

        String expectedMessage = "Thursday 01 January 3914 08:15:00.000+0200 method: updateListings parameters: someParam 1,someList [Test], error: Something went wrong";
        verify(logger).error(eq(Markers.METHOD_START()), eq(expectedMessage));
    }
}
