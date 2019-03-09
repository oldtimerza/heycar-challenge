package com.heycar.heycarchallenge.mapping;

import com.heycar.heycarchallenge.domain.dto.CsvListing;
import com.heycar.heycarchallenge.domain.dto.CsvMakeModel;
import com.heycar.heycarchallenge.domain.entity.Listing;
import org.junit.Assert;
import org.junit.Test;

public class ListingMapperTest {
    private ListingMapper mapper = ListingMapper.INSTANCE;

    @Test
    public void map_whenGivenCsvListings_shouldReturnListings() {
        CsvListing csvListing = new CsvListing();
        String color = "red";
        String code = "code";
        Long kW = 132L;
        Long price = 13990L;
        Long year = 2014L;
        CsvMakeModel makeModel = new CsvMakeModel("mercedes/a 180");
        csvListing.setCode(code);
        csvListing.setColor(color);
        csvListing.setKiloWatts(kW.toString());
        csvListing.setMakeModel(makeModel);
        csvListing.setPrice(price.toString());
        csvListing.setYear(year.toString());

        Listing listing = mapper.map(csvListing);

        Assert.assertEquals(code, listing.getCode());
        Assert.assertEquals(makeModel.getMake(), listing.getMake());
        Assert.assertEquals(makeModel.getModel(), listing.getModel());
        Assert.assertEquals(kW, listing.getKiloWatts());
        Assert.assertEquals(color, listing.getColor());
        Assert.assertEquals(year, listing.getYear());
    }

    @Test
    public void map_nullCsvListing_shouldReturnNull(){
        Listing listing = mapper.map(null);

        Assert.assertNull(listing);
    }
}
