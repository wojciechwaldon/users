package com.wojciechwaldon.users.domain.api.manager.find;

import com.wojciechwaldon.cqrs.api.query.Query;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindManagerQuery implements Query<FindManagerQueryView> {

    @NonNull
    private FindManagerQueryView view;
}
