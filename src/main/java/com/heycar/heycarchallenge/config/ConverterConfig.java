package com.heycar.heycarchallenge.config;

import com.heycar.heycarchallenge.converting.CsvListingsConverter;
import com.heycar.heycarchallenge.domain.dto.CsvListing;
import com.heycar.heycarchallenge.domain.dto.CsvListings;
import com.heycar.heycarchallenge.mapping.CustomCsvMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class ConverterConfig {
    @Bean
    public HttpMessageConverter<CsvListings> createCustomCsvConverter(CustomCsvMapper<CsvListing> customCsvMapper) {
        return new CsvListingsConverter(customCsvMapper);
    }
}
