package com.heycar.listings.mapping;

import com.heycar.heycarchallenge.domain.dto.CsvListing;
import com.heycar.heycarchallenge.mapping.CsvListingMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class CsvListingMapperTest {
    @Test
    public void map_whenGivenCsvData_shouldReturnListCsvListings() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("csv-listings.csv").getFile());
        FileInputStream stream = new FileInputStream(file);
        CsvListingMapper mapper = new CsvListingMapper();

        List<CsvListing> csvListings = mapper.map(stream);

        Assert.assertEquals(4, csvListings.size());
        CsvListing firstListing = csvListings.get(0);
        Assert.assertEquals("1", firstListing.getCode());
        Assert.assertEquals("mercedes", firstListing.getMakeModel().getMake());
        Assert.assertEquals("a 180", firstListing.getMakeModel().getModel());
        Assert.assertEquals("123", firstListing.getKiloWatts());
        Assert.assertEquals("2014", firstListing.getYear());
        Assert.assertEquals("black", firstListing.getColor());
        Assert.assertEquals("15950", firstListing.getPrice());
    }
}
