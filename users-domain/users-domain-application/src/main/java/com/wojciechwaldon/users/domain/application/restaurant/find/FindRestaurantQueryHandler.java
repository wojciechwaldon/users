package com.wojciechwaldon.users.domain.application.restaurant.find;

import com.wojciechwaldon.cqrs.api.query.QueryHandler;
import com.wojciechwaldon.users.domain.api.restaurant.Restaurant;
import com.wojciechwaldon.users.domain.api.restaurant.find.FindRestaurantQuery;
import com.wojciechwaldon.users.domain.api.restaurant.find.FindRestaurantQueryView;
import com.wojciechwaldon.users.domain.application.restaurant.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class FindRestaurantQueryHandler implements QueryHandler<FindRestaurantQuery, FindRestaurantQueryView> {

    private RestaurantRepository restaurantRepository;

    @Transactional
    @Override
    public FindRestaurantQueryView handle(FindRestaurantQuery findRestaurantQuery) throws Exception {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurant(findRestaurantQuery.getId());


        final Restaurant restaurant = optionalRestaurant.orElseThrow(() -> {
            log.error("Restaurant with id: {} not found", findRestaurantQuery.getId());
            return new IllegalArgumentException("Restaurant not found");
        });

        return FindRestaurantQueryView.of(restaurant.getId(),
                restaurant.getName());
    }
}
