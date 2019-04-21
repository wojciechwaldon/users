package com.wojciechwaldon.users.domain.api.employee.save;

import com.wojciechwaldon.cqrs.api.command.Command;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.domain.api.employee.Employee;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class SaveEmployeeCommand implements Command {

    @Valid
    @NotNull
    private Employee employee;
}
