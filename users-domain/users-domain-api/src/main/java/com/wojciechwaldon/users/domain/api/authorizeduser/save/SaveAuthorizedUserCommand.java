package com.wojciechwaldon.users.domain.api.authorizeduser.save;

import com.wojciechwaldon.cqrs.api.command.Command;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class SaveAuthorizedUserCommand implements Command {

    @Valid
    @NotNull
    private AuthorizedUser authorizedUser;
}
