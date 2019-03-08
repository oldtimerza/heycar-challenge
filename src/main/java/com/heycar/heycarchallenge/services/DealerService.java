package com.heycar.heycarchallenge.services;

import com.heycar.heycarchallenge.domain.entity.Dealer;
import com.heycar.heycarchallenge.domain.entity.Listing;
import com.heycar.heycarchallenge.domain.error.NonExistentDealerException;
import com.heycar.heycarchallenge.domain.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    @Autowired
    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public Dealer updateListings(Long dealerId, List<Listing> listings) {
        Optional<Dealer> possibleDealer = dealerRepository.findById(dealerId);
        if (!possibleDealer.isPresent()) {
            throw new NonExistentDealerException(dealerId);
        }

        Dealer dealer = possibleDealer.get();
        listings.forEach(listing -> createOrUpdate(dealer, listing));

        dealerRepository.saveAndFlush(dealer);

        return dealer;
    }

    private void createOrUpdate(Dealer dealer, Listing listing) {
        List<Listing> currentListings = dealer.getListings();
        Stream<Listing> dealerListingsStream = currentListings.stream();
        Optional<Listing> possibleListing = dealerListingsStream.filter(dealerListing -> dealerListing.hashCode() == listing.hashCode()).findFirst();
        if (possibleListing.isPresent()) {
            Listing existingListing = possibleListing.get();
            existingListing.setCode(listing.getCode());
            existingListing.setColor(listing.getColor());
            existingListing.setKiloWatts(listing.getKiloWatts());
            existingListing.setMake(listing.getMake());
            existingListing.setModel(listing.getModel());
            existingListing.setYear(listing.getYear());
            existingListing.setPrice(listing.getPrice());
            existingListing.setDealer(dealer);
        } else {
            listing.setDealer(dealer);
            currentListings.add(listing);
        }
    }
}
