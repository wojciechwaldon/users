package com.wojciechwaldon.users.infrastructure.delivery.restaurant;


import com.wojciechwaldon.commons.json.JsonToObjectConverter;
import com.wojciechwaldon.commons.json.ObjectToJsonConverter;
import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.infrastructure.CqrsConfiguration;
import com.wojciechwaldon.users.domain.api.restaurant.Restaurant;
import com.wojciechwaldon.users.domain.api.restaurant.save.SaveRestaurantCommand;
import com.wojciechwaldon.users.infrastructure.UsersModuleConfiguration;
import com.wojciechwaldon.users.infrastructure.repository.restaurant.RestaurantRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(classes = {UsersModuleConfiguration.class, CqrsConfiguration.class})
public class RestaurantHttpEndpointsIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private RestaurantRepositoryImpl restaurantRepository;

    @Spy
    private JsonToObjectConverter<Restaurant> restaurantJsonToObjectConverter;

    @Spy
    private ObjectToJsonConverter objectToJsonConverter;

    @Before
    public void cleanUp() {
        restaurantRepository.deleteAll();
    }

    @Test
    public void shouldSaveRestaurant() throws Exception {
        Restaurant restaurant = RestaurantFactory.build();

        this.mockMvc.perform(post("/restaurant")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectToJsonConverter.convert(restaurant)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFindRestaurant() throws Exception {
        //given
        Restaurant restaurant = RestaurantFactory.build();

        //when
        commandExecutor.execute(SaveRestaurantCommand.of(restaurant));

        //then
        ResultActions resultActions = this.mockMvc.perform(get("/restaurant/".concat(restaurant.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        assertRestaurantResult(restaurant, resultActions);
    }

    private void assertRestaurantResult(Restaurant restaurant, ResultActions resultActions) throws UnsupportedEncodingException {
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Restaurant restaurantResult = restaurantJsonToObjectConverter.convert(contentAsString, Restaurant.class);

        assertEquals(restaurant, restaurantResult);
    }
}
