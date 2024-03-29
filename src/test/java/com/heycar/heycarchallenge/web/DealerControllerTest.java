package com.heycar.heycarchallenge.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heycar.heycarchallenge.converting.CsvListingsConverter;
import com.heycar.heycarchallenge.domain.dto.CsvListing;
import com.heycar.heycarchallenge.domain.entity.Dealer;
import com.heycar.heycarchallenge.domain.entity.Listing;
import com.heycar.heycarchallenge.domain.error.ApiError;
import com.heycar.heycarchallenge.logging.LogBuilder;
import com.heycar.heycarchallenge.mapping.CustomCsvMapper;
import com.heycar.heycarchallenge.mapping.ListingMapper;
import com.heycar.heycarchallenge.services.DealerService;
import com.heycar.heycarchallenge.utils.CsvTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class DealerControllerTest {

    @Mock
    private DealerService dealerService;

    @Mock
    private LogBuilder logBuilder;

    @Mock
    private ListingMapper listingMapper;

    @InjectMocks
    private DealerController dealerController;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private String listingJson;

    private String csvContents;

    private Dealer dealer;

    private List<Listing> listings;

    @Before
    public void setup() throws IOException {
        when(logBuilder.withLogger(any())).thenReturn(logBuilder);
        when(logBuilder.withMethod(any())).thenReturn(logBuilder);
        when(logBuilder.withDate(any())).thenReturn(logBuilder);
        when(logBuilder.withMarker(any())).thenReturn(logBuilder);
        when(logBuilder.withError(any())).thenReturn(logBuilder);
        when(logBuilder.withParameter(any(), any())).thenReturn(logBuilder);
        doNothing().when(logBuilder).info();
        doNothing().when(logBuilder).error();

        mapper = new ObjectMapper();

        Long id = 1L;
        Listing listing = new Listing();
        listing.setCode("a");
        listing.setMake("renault");
        listing.setModel("megane");
        listing.setKiloWatts(132L);
        listing.setYear(2014L);
        listing.setColor("red");
        listing.setPrice(13990L);
        listings = new ArrayList<>();
        listings.add(listing);
        listingJson = mapper.writeValueAsString(listings);

        dealer = new Dealer();
        dealer.setId(id);
        dealer.setListings(listings);
        when(dealerService.updateListings(any(), any())).thenReturn(dealer);

        when(listingMapper.map(any())).thenReturn(listing);

        CsvTestUtils testUtils = new CsvTestUtils();
        csvContents = testUtils.readCsvFile("controller-csv.csv");
        CustomCsvMapper<CsvListing> csvMapper = new CustomCsvMapper<>(CsvListing.class);
        CsvListingsConverter csvListingsConverter = new CsvListingsConverter(csvMapper);
        MappingJackson2HttpMessageConverter defaultJsonConverter = new MappingJackson2HttpMessageConverter();
        mockMvc = MockMvcBuilders
                .standaloneSetup(dealerController)
                .setMessageConverters(defaultJsonConverter, csvListingsConverter)
                .build();
    }

    @Test
    public void updateListingsJson_whenGivenListings_shouldCallServiceUpdateListings() throws Exception {
        ResultActions result = mockMvc.perform(post("/vehicle_listings/{dealerId}", dealer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(listingJson)
                .accept(MediaType.APPLICATION_JSON));

        String expectedResponse = mapper.writeValueAsString(dealer);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedResponse));
        verify(dealerService).updateListings(eq(dealer.getId()), eq(listings));
    }

    @Test
    public void updateListingsJson_whenAnErrorOccurs_shouldReturnAnError() throws Exception {
        RuntimeException exception = new RuntimeException("Something went wrong");
        when(dealerService.updateListings(any(), anyList())).thenThrow(exception);

        ResultActions result = mockMvc.perform(post("/vehicle_listings/{dealerId}", dealer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(listingJson)
                .accept(MediaType.APPLICATION_JSON));


        ApiError expectedError = ApiError.fromException(exception);
        String errorJson = mapper.writeValueAsString(expectedError);
        result.andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(errorJson));
    }

    @Test
    public void updateListingsCsv_whenGivenCsvData_shouldCallUpdateListings() throws Exception {
        ResultActions result = mockMvc.perform(post("/upload_csv/{dealerId}", dealer.getId())
                .contentType(CsvListingsConverter.MEDIA_TYPE)
                .content(csvContents)
                .accept(MediaType.APPLICATION_JSON));

        String expectedResponse = mapper.writeValueAsString(dealer);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedResponse));
        verify(dealerService).updateListings(eq(dealer.getId()), eq(listings));
    }


    @Test
    public void updateListingsCsv_whenAnErrorOccurs_shouldReturnAnError() throws Exception {
        RuntimeException exception = new RuntimeException("Something went wrong");
        when(dealerService.updateListings(any(), anyList())).thenThrow(exception);

        ResultActions result = mockMvc.perform(post("/upload_csv/{dealerId}", dealer.getId())
                .contentType(CsvListingsConverter.MEDIA_TYPE)
                .content(csvContents)
                .accept(MediaType.APPLICATION_JSON));


        ApiError expectedError = ApiError.fromException(exception);
        String errorJson = mapper.writeValueAsString(expectedError);
        result.andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(errorJson));
    }
}
