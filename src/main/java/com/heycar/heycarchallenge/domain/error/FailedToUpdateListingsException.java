package com.heycar.heycarchallenge.domain.error;

public class FailedToUpdateListingsException extends RuntimeException{
    public FailedToUpdateListingsException(Long dealerId){
        super(String.format("Failed to update listings for dealer: {}", dealerId));
    }
}
