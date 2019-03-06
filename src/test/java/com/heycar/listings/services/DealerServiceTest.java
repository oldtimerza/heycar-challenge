package com.heycar.listings.services;

import com.heycar.heycarchallenge.domain.entity.Dealer;
import com.heycar.heycarchallenge.domain.entity.Listing;
import com.heycar.heycarchallenge.domain.repository.DealerRepository;
import com.heycar.heycarchallenge.services.DealerService;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DealerServiceTest {
    private Long dealerId;

    private Dealer dealer;

    private List<Listing> listings;

    @Mock
    private DealerRepository dealerRepository;

    @InjectMocks
    private DealerService dealerService;

    @Before
    public void setup() {
        listings = new ArrayList<>();
        Listing listing = new Listing();
        listing.setPrice(1L);
        listing.setYear(2014L);
        listing.setModel("Figo");
        listing.setMake("Ford");
        listing.setKiloWatts(140L);
        listing.setColor("white");
        listing.setDealerId(dealerId);
        listing.setCode("code");
        listings.add(listing);
        dealer = new Dealer();
        dealer.setId(dealerId);
        dealer.setListings(listings);
        Optional<Dealer> possibleDealer = Optional.of(dealer);
        when(dealerRepository.findById(eq(dealerId))).thenReturn(possibleDealer);
    }

    @Test
    public void updateListings_withNewListing_shouldCreateNewListing() {
        Listing newListing = new Listing();
        List<Listing> newListings = Arrays.asList(newListing);

        dealerService.updateListings(dealerId, newListings);

        Assert.assertEquals(2, dealer.getListings().size());
    }
}
