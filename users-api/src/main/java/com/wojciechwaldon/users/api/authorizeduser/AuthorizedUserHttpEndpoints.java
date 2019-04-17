package com.wojciechwaldon.users.api.authorizeduser;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.domain.api.authorizeduser.find.FindAuthorizedUserQuery;
import com.wojciechwaldon.users.domain.api.authorizeduser.find.FindAuthorizedUserQueryView;
import com.wojciechwaldon.users.domain.api.authorizeduser.save.SaveAuthorizedUserCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthorizedUserHttpEndpoints {

    private CommandExecutor commandExecutor;
    private QueryExecutor queryExecutor;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void saveUser(@RequestBody AuthorizedUser authorizedUser) throws Exception {
        SaveAuthorizedUserCommand saveAuthorizedUserCommand = SaveAuthorizedUserCommand.of(authorizedUser);

        commandExecutor.execute(saveAuthorizedUserCommand);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public FindAuthorizedUserQueryView findUser(@PathVariable Long id) throws Exception {
        FindAuthorizedUserQuery findAuthorizedUserQuery = FindAuthorizedUserQuery.of(id);

        return queryExecutor.execute(findAuthorizedUserQuery);
    }
}
