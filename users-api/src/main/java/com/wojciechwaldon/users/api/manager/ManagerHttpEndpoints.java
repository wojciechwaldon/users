package com.wojciechwaldon.users.api.manager;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import com.wojciechwaldon.users.api.UsersModuleHttpEndpoint;
import com.wojciechwaldon.users.domain.api.manager.Manager;
import com.wojciechwaldon.users.domain.api.manager.find.FindManagerQuery;
import com.wojciechwaldon.users.domain.api.manager.find.FindManagerQueryView;
import com.wojciechwaldon.users.domain.api.manager.save.SaveManagerCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/manager",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagerHttpEndpoints extends UsersModuleHttpEndpoint {

    public ManagerHttpEndpoints(CommandExecutor commandExecutor,
                                QueryExecutor queryExecutor) {
        super(commandExecutor, queryExecutor);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void saveManager(@RequestBody Manager manager) throws Exception {
        SaveManagerCommand saveManagerCommand = SaveManagerCommand.of(manager);

        commandExecutor.execute(saveManagerCommand);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public FindManagerQueryView findManager(@PathVariable Long id) throws Exception {
        FindManagerQuery findManagerQuery = FindManagerQuery.of(id);

        return queryExecutor.execute(findManagerQuery);
    }
}
