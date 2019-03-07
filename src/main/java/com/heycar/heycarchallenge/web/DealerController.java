package com.heycar.heycarchallenge.web;

import com.heycar.heycarchallenge.domain.entity.Dealer;
import com.heycar.heycarchallenge.domain.entity.Listing;
import com.heycar.heycarchallenge.domain.error.FailedToUpdateListingsException;
import com.heycar.heycarchallenge.logging.LogBuilder;
import com.heycar.heycarchallenge.logging.Markers;
import com.heycar.heycarchallenge.services.DealerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class DealerController {
    private Logger logger = LoggerFactory.getLogger(DealerController.class);

    @Autowired
    private LogBuilder logBuilder;

    @Autowired
    private DealerService dealerService;

    @RequestMapping(value = "/vehicle-listings/{dealerId}", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Dealer updateListings(@PathVariable("dealerId") Long dealerId, @RequestBody List<Listing> listings) {
        logBuilder
                .withLogger(logger)
                .withMarker(Markers.METHOD_START())
                .withDate(new Date())
                .withMethod("updateListings")
                .withParameter("dealerId", dealerId)
                .withParameter("listings", listings)
                .info();
        try{
            Dealer dealer = dealerService.updateListings(dealerId, listings);
            logBuilder
                    .withLogger(logger)
                    .withMarker(Markers.METHOD_END())
                    .withDate(new Date())
                    .withMethod("updateListings")
                    .withParameter("dealerId", dealerId)
                    .withParameter("listings", listings)
                    .info();
            return dealer;
        }catch(Exception e){
            logBuilder
                    .withLogger(logger)
                    .withMarker(Markers.METHOD_END())
                    .withDate(new Date())
                    .withMethod("updateListings")
                    .withParameter("dealerId", dealerId)
                    .withParameter("listings", listings)
                    .withError(e)
                    .error();
            throw new FailedToUpdateListingsException(dealerId);
        }
    }
}
