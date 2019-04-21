package com.wojciechwaldon.users.api;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public abstract class UsersModuleHttpEndpoint {

    protected CommandExecutor commandExecutor;
    protected QueryExecutor queryExecutor;
}
