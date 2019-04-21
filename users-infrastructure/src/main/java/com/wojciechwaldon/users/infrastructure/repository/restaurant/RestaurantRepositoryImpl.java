package com.wojciechwaldon.users.infrastructure.repository.restaurant;

import com.wojciechwaldon.users.domain.api.restaurant.Restaurant;
import com.wojciechwaldon.users.domain.application.restaurant.RestaurantRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepositoryImpl extends RestaurantRepository,
        CrudRepository<Restaurant, Long> {

    default void saveRestaurant(Restaurant restaurant) {
        save(restaurant);
    }

    default Optional<Restaurant> findRestaurant(Long id) {
        return findById(id);
    }
}
