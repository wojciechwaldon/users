package com.wojciechwaldon.users.domain.api.employee.find;

import com.wojciechwaldon.cqrs.api.query.Query;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindEmployeeQuery implements Query<FindEmployeeQueryView> {

    @NonNull
    private FindEmployeeQueryView view;
}
