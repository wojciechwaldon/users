package com.wojciechwaldon.users.domain.api.restaurant.find;

import com.wojciechwaldon.cqrs.api.query.QueryView;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindRestaurantQueryView implements QueryView {

    @NotNull
    private Long id;

    @NotNull
    private String name;
}
