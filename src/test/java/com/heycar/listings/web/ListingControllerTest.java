package com.heycar.listings.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heycar.heycarchallenge.domain.entity.Listing;
import com.heycar.heycarchallenge.domain.error.ApiError;
import com.heycar.heycarchallenge.logging.LogBuilder;
import com.heycar.heycarchallenge.services.ListingService;
import com.heycar.heycarchallenge.web.ListingController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ListingControllerTest {

    @Mock
    private ListingService listingService;

    @Mock
    private LogBuilder logBuilder;

    @InjectMocks
    private ListingController listingController;

    private MockMvc mockMvc;

    private List<Listing> listings;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        when(logBuilder.withLogger(any())).thenReturn(logBuilder);
        when(logBuilder.withMethod(any())).thenReturn(logBuilder);
        when(logBuilder.withDate(any())).thenReturn(logBuilder);
        when(logBuilder.withMarker(any())).thenReturn(logBuilder);
        when(logBuilder.withError(any())).thenReturn(logBuilder);
        when(logBuilder.withParameter(any(), any())).thenReturn(logBuilder);
        doNothing().when(logBuilder).info();
        doNothing().when(logBuilder).error();

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

        mapper = new ObjectMapper();

        mockMvc = MockMvcBuilders
                .standaloneSetup(listingController)
                .build();
    }

    @Test
    public void search_whenGivenMakeModelYearColor_shouldReturnExpectedResults() throws Exception {
        String make = listings.get(0).getMake();
        String model = listings.get(0).getModel();
        Long year = listings.get(0).getYear();
        String color = listings.get(0).getColor();
        Integer offset = 0;
        Integer limit = 1;
        Page<Listing> pagedListings = new PageImpl<>(listings);
        when(listingService.search(eq(make), eq(model), eq(year), eq(color), eq(offset), eq(limit))).thenReturn(pagedListings);

        ResultActions result = mockMvc.perform(get("/search")
                .param("make", make)
                .param("model", model)
                .param("year", year.toString())
                .param("color", color)
                .param("offset", offset.toString())
                .param("limit", limit.toString())
                .accept(MediaType.APPLICATION_JSON)
        );

        String expectedJson = mapper.writeValueAsString(pagedListings);

        result.andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }


    @Test
    public void search_whenAnErrorOccurs_shouldReturnAnApiError() throws Exception {
        RuntimeException exception = new RuntimeException("Something went wrong");
        when(listingService.search(any(), any(), any(), any(), anyInt(), anyInt())).thenThrow(exception);

        ResultActions result = mockMvc.perform(get("/search")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        ApiError expectedError = ApiError.fromException(exception);
        String errorJson = mapper.writeValueAsString(expectedError);
        result.andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(errorJson));
    }
}
