package com.heycar.heycarchallenge.domain.error;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ApiErrorTest {
    @Test
    public void fromException_whenGivenException_shouldReturnFormattedInternalServerError(){
        String message = "something went wrong";
        Exception exception = new Exception(message);

        ApiError apiError = ApiError.fromException(exception);

        Assert.assertEquals(message, apiError.getMessage());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, apiError.getStatus());
    }
}
