package com.heycar.heycarchallenge.mapping;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.heycar.heycarchallenge.domain.dto.CsvListing;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class CsvListingMapper {

    private ObjectReader objectReader;

    public CsvListingMapper() {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        objectReader = csvMapper.readerFor(CsvListing.class).with(bootstrapSchema);
    }

    public List<CsvListing> map(InputStream stream) throws IOException {
        return objectReader.<CsvListing>readValues(stream).readAll();
    }
}
