package com.heycar.heycarchallenge.services;

import com.heycar.heycarchallenge.domain.entity.Dealer;
import com.heycar.heycarchallenge.domain.entity.Listing;
import com.heycar.heycarchallenge.domain.error.NonExistentDealerException;
import com.heycar.heycarchallenge.domain.repository.DealerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DealerServiceTest {
    private Long dealerId = 1L;

    private Dealer dealer;

    private List<Listing> listings;

    private Listing listing;

    @Mock
    private DealerRepository dealerRepository;

    @InjectMocks
    private DealerService dealerService;

    @Before
    public void setup() {
        listings = new ArrayList<>();

        listing = new Listing();
        listing.setPrice(1L);
        listing.setYear(2014L);
        listing.setModel("Figo");
        listing.setMake("Ford");
        listing.setKiloWatts(140L);
        listing.setColor("white");
        listing.setDealer(dealer);
        listing.setCode("code");
        listings.add(listing);

        dealer = new Dealer();
        dealer.setId(dealerId);
        dealer.setListings(listings);

        Optional<Dealer> possibleDealer = Optional.of(dealer);

        when(dealerRepository.findById(eq(dealerId))).thenReturn(possibleDealer);
    }

    @Test(expected = NonExistentDealerException.class)
    public void updateListings_withNonExistantDealer_shouldThrowError(){
        Long nonExistentDealerId = 999L;

        dealerService.updateListings(nonExistentDealerId, new ArrayList<>());
    }

    @Test
    public void updateListings_withNewListing_shouldCreateNewListing() {
        Listing newListing = new Listing();
        List<Listing> newListings = Arrays.asList(newListing);

        dealerService.updateListings(dealerId, newListings);

        List<Listing> expectedListings = Arrays.asList(listing, newListing);
        Assert.assertEquals(expectedListings, dealer.getListings());

        verify(dealerRepository).saveAndFlush(eq(dealer));
    }

    @Test
    public void updateListings_withExistingListing_shouldUpdateCurrentListing(){
        Listing updatedListing = new Listing();
        updatedListing.setCode(listing.getCode());
        updatedListing.setPrice(1L);
        updatedListing.setYear(2014L);
        updatedListing.setModel("NotAFigo");
        updatedListing.setMake("NotAFord");
        updatedListing.setKiloWatts(140L);
        updatedListing.setColor("red");
        updatedListing.setDealer(dealer);
        List<Listing> newListings = Arrays.asList(updatedListing);

        dealerService.updateListings(dealerId, newListings);

        List<Listing> expectedListings = Arrays.asList(updatedListing);
        Assert.assertEquals(expectedListings, dealer.getListings());

        verify(dealerRepository).saveAndFlush(eq(dealer));
    }
}
