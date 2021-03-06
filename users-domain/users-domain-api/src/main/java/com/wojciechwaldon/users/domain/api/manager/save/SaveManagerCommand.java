package com.wojciechwaldon.users.domain.api.manager.save;

import com.wojciechwaldon.cqrs.api.command.Command;
import com.wojciechwaldon.users.domain.api.manager.Manager;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class SaveManagerCommand implements Command {

    @Valid
    @NotNull
    private Manager manager;
}
