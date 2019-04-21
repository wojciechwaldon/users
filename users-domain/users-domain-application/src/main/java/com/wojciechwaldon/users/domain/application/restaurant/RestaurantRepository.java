package com.wojciechwaldon.users.domain.application.restaurant;

import com.wojciechwaldon.users.domain.api.restaurant.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    void saveRestaurant(Restaurant restaurant);

    Optional<Restaurant> findRestaurant(Long id);
}
