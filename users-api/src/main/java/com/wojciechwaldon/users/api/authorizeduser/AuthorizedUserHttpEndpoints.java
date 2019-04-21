package com.wojciechwaldon.users.api.authorizeduser;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import com.wojciechwaldon.users.api.UsersModuleHttpEndpoint;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.domain.api.authorizeduser.find.FindAuthorizedUserQuery;
import com.wojciechwaldon.users.domain.api.authorizeduser.find.FindAuthorizedUserQueryView;
import com.wojciechwaldon.users.domain.api.authorizeduser.save.SaveAuthorizedUserCommand;
import com.wojciechwaldon.users.domain.api.authorizeduser.save.SaveOrUpdateAuthorizedUserCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/user",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorizedUserHttpEndpoints extends UsersModuleHttpEndpoint {

    public AuthorizedUserHttpEndpoints(CommandExecutor commandExecutor,
                                       QueryExecutor queryExecutor) {
        super(commandExecutor, queryExecutor);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void saveUser(@RequestBody AuthorizedUser authorizedUser) throws Exception {
        SaveAuthorizedUserCommand saveAuthorizedUserCommand = SaveAuthorizedUserCommand.of(authorizedUser);

        commandExecutor.execute(saveAuthorizedUserCommand);
    }

    @PostMapping(path = "/update")
    public void saveUserOrUpdate(@RequestBody AuthorizedUser authorizedUser) throws Exception {
        SaveOrUpdateAuthorizedUserCommand saveAuthorizedUserCommand = SaveOrUpdateAuthorizedUserCommand.of(authorizedUser);

        commandExecutor.execute(saveAuthorizedUserCommand);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public FindAuthorizedUserQueryView findUser(@PathVariable Long id) throws Exception {
        FindAuthorizedUserQuery findAuthorizedUserQuery = FindAuthorizedUserQuery.of(id);

        return queryExecutor.execute(findAuthorizedUserQuery);
    }
}
