package com.heycar.listings.web;

import com.heycar.heycarchallenge.logging.LogBuilder;
import com.heycar.heycarchallenge.services.DealerService;
import com.heycar.heycarchallenge.web.DealerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
public class DealerControllerTest {

    @Mock
    private DealerService dealerService;

    @Mock
    private LogBuilder logBuilder;

    @InjectMocks
    private DealerController dealerController;

    private MockMvc mockMvc;

    @Test
    public void updateListings_whenGivenListings_shouldCallServiceUpdateListings(){

    }
}
