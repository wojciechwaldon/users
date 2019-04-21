package com.wojciechwaldon.users.domain.api.employee.find;

import com.wojciechwaldon.cqrs.api.query.QueryView;
import com.wojciechwaldon.users.domain.api.Token;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindEmployeeQueryView implements QueryView {

    @NonNull
    private Long id;

    private Token token;

    @NonNull
    protected String firstName;

    @NonNull
    protected String lastName;

    @NonNull
    protected String email;

    @NonNull
    protected String telephone;

    @NonNull
    private String password;

    @NonNull
    private Long restaurantId;
}
