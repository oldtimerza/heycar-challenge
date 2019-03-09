package com.heycar.listings.mapping;

import com.heycar.heycarchallenge.domain.dto.CsvListing;
import com.heycar.heycarchallenge.domain.dto.CsvMakeModel;
import com.heycar.heycarchallenge.domain.entity.Listing;
import com.heycar.heycarchallenge.mapping.ListingMapper;
import org.junit.Assert;
import org.junit.Test;

public class ListingMapperTest {
    @Test
    public void map_whenGivenCsvListings_shouldReturnListings() {
        ListingMapper mapper = ListingMapper.INSTANCE;
        CsvListing csvListing = new CsvListing();
        String color = "red";
        String code = "code";
        String kW = "132";
        String price = "13990";
        String year = "2014";
        CsvMakeModel makeModel = new CsvMakeModel("mercedes/a 180");
        csvListing.setCode(code);
        csvListing.setColor(color);
        csvListing.setKiloWatts(kW);
        csvListing.setMakeModel(makeModel);
        csvListing.setPrice(price);
        csvListing.setYear(year);

        Listing listing = mapper.map(csvListing);

        Assert.assertEquals(code, listing.getCode());
    }
}
