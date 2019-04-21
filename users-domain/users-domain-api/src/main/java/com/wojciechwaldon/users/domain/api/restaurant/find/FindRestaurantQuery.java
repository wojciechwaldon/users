package com.wojciechwaldon.users.domain.api.restaurant.find;

import com.wojciechwaldon.cqrs.api.query.Query;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindRestaurantQuery implements Query<FindRestaurantQueryView> {

    @NotNull
    private Long id;
}
