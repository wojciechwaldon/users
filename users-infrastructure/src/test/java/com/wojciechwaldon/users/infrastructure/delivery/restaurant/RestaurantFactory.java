package com.wojciechwaldon.users.infrastructure.delivery.restaurant;

import com.wojciechwaldon.users.domain.api.restaurant.Restaurant;

class RestaurantFactory {

    static Restaurant build() {
        return Restaurant.of("name");

    }
}
