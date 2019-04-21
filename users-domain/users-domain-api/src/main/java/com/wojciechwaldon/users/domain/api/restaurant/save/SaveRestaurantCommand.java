package com.wojciechwaldon.users.domain.api.restaurant.save;

import com.wojciechwaldon.cqrs.api.command.Command;
import com.wojciechwaldon.users.domain.api.restaurant.Restaurant;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class SaveRestaurantCommand implements Command {

    @NotNull
    @Valid
    private Restaurant restaurant;
}
