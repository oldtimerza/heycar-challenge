package com.heycar.heycarchallenge.config;

import com.heycar.heycarchallenge.domain.dto.CsvListing;
import com.heycar.heycarchallenge.mapping.CustomCsvMapper;
import com.heycar.heycarchallenge.mapping.ListingMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {
    @Bean
    public CustomCsvMapper<CsvListing> customCsvMapper(){
        return new CustomCsvMapper<>(CsvListing.class);
    }

    @Bean
    public ListingMapper listingMapper(){
        return ListingMapper.INSTANCE;
    }
}
