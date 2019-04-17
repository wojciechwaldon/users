package com.wojciechwaldon.users.domain.api.authorizeduser.find;

import com.wojciechwaldon.cqrs.api.query.Query;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindAuthorizedUserQuery implements Query<FindAuthorizedUserQueryView> {

    @NonNull
    private Long id;
}
