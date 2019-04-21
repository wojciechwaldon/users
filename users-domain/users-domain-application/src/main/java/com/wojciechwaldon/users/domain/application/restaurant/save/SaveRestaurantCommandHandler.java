package com.wojciechwaldon.users.domain.application.restaurant.save;

import com.wojciechwaldon.cqrs.api.command.CommandHandler;
import com.wojciechwaldon.users.domain.api.restaurant.save.SaveRestaurantCommand;
import com.wojciechwaldon.users.domain.application.restaurant.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class SaveRestaurantCommandHandler implements CommandHandler<SaveRestaurantCommand> {

    private RestaurantRepository restaurantRepository;

    @Transactional
    @Override
    public void handle(SaveRestaurantCommand saveRestaurantCommand) throws Exception {
        restaurantRepository.saveRestaurant(saveRestaurantCommand.getRestaurant());
    }
}
