package com.heycar.heycarchallenge.services;

import com.heycar.heycarchallenge.domain.entity.Listing;
import com.heycar.heycarchallenge.domain.repository.ListingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListingServiceTest {
    @Mock
    private ListingRepository listingRepository;

    @InjectMocks
    private ListingService listingService;

    @Test
    public void search_withMake_shouldCallRepositoryWithExample() {
        String make = "someMake";
        String model = "someModel";
        Long year = 2014L;
        String color = "red";
        int offset = 0;
        int limit = 1;

        Page<Listing> listings = listingService.search(make, model, year, color, offset, limit);

        Listing expectedExample = new Listing();
        expectedExample.setMake(make);
        expectedExample.setModel(model);
        expectedExample.setYear(year);
        expectedExample.setColor(color);
        Pageable pageable = PageRequest.of(offset, limit);
        verify(listingRepository).findAll(eq(Example.of(expectedExample)), eq(pageable));
    }
}
