package com.wojciechwaldon.users.api.restaurant;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import com.wojciechwaldon.users.api.UsersModuleHttpEndpoint;
import com.wojciechwaldon.users.domain.api.restaurant.Restaurant;
import com.wojciechwaldon.users.domain.api.restaurant.find.FindRestaurantQuery;
import com.wojciechwaldon.users.domain.api.restaurant.find.FindRestaurantQueryView;
import com.wojciechwaldon.users.domain.api.restaurant.save.SaveRestaurantCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/restaurant",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantHttpEndpoint extends UsersModuleHttpEndpoint {

    public RestaurantHttpEndpoint(CommandExecutor commandExecutor,
                                  QueryExecutor queryExecutor) {
        super(commandExecutor, queryExecutor);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void saveRestaurant(@RequestBody Restaurant restaurant) throws Exception {
        SaveRestaurantCommand saveRestaurantCommand = SaveRestaurantCommand.of(restaurant);

        commandExecutor.execute(saveRestaurantCommand);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public FindRestaurantQueryView findRestaurant(@PathVariable Long id) throws Exception {
        FindRestaurantQuery findRestaurantQuery = FindRestaurantQuery.of(id);

        return queryExecutor.execute(findRestaurantQuery);
    }
}
