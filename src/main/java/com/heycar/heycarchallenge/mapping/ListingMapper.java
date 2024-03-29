package com.heycar.heycarchallenge.mapping;

import com.heycar.heycarchallenge.domain.dto.CsvListing;
import com.heycar.heycarchallenge.domain.entity.Listing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListingMapper {
    ListingMapper INSTANCE = Mappers.getMapper(ListingMapper.class);

    @Mapping(source = "csvListing.makeModel.make", target = "make")
    @Mapping(source = "csvListing.makeModel.model", target = "model")
    Listing map(CsvListing csvListing);
}
