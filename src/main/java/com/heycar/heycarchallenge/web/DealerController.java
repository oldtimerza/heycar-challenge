package com.heycar.heycarchallenge.web;

import com.heycar.heycarchallenge.domain.entity.Dealer;
import com.heycar.heycarchallenge.domain.entity.Listing;
import com.heycar.heycarchallenge.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @RequestMapping(value = "/vehicle-listings/{dealerId}", method = RequestMethod.POST)
    public Dealer updateListings(@PathVariable("dealerId") Long dealerId, @RequestBody List<Listing> Listings) {
        return null;
    }
}
