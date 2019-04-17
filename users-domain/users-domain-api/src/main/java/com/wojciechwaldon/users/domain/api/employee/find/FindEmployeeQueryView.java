package com.wojciechwaldon.users.domain.api.employee.find;

import com.wojciechwaldon.cqrs.api.query.QueryView;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindEmployeeQueryView implements QueryView {

    @NonNull
    private Long id;
}
