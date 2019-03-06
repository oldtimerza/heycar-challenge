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

    private DealerRepository dealerRepository;

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
        listings.forEach(listing -> createOrUpdate(dealer.getListings(), listing));
        return dealer;
    }

    private void createOrUpdate(List<Listing> dealerListings, Listing listing) {
        Stream<Listing> dealerListingsStream = dealerListings.stream();
        Optional<Listing> possibleListing = dealerListingsStream.filter(dealerListing -> dealerListing.equals(listing)).findFirst();
        if(possibleListing.isPresent()){
            //update it
            Listing existingListing = possibleListing.get();
            existingListing.setCode(listing.getCode());
            existingListing.setColor(listing.getColor());
            existingListing.setKiloWatts(listing.getKiloWatts());
            existingListing.setMake(listing.getMake());
            existingListing.setModel(listing.getModel());
            existingListing.setYear(listing.getYear());
            existingListing.setPrice(listing.getPrice());
        }
        //otherwise create it
        dealerListings.add(listing);
    }
}
