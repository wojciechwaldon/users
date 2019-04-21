package com.wojciechwaldon.users.domain.api.authorizeduser.find;

import com.wojciechwaldon.cqrs.api.query.QueryView;
import com.wojciechwaldon.users.domain.api.token.Token;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindAuthorizedUserQueryView implements QueryView {

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
}
