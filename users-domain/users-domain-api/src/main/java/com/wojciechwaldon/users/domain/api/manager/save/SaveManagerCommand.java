package com.wojciechwaldon.users.domain.api.manager.save;

import com.wojciechwaldon.cqrs.api.command.Command;
import com.wojciechwaldon.users.domain.api.Token;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class SaveManagerCommand implements Command {

    private Long id;

    private Token token;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String telephone;

    @NotNull
    private String password;

    @NotNull
    private String restaurantId;
}
