package com.wojciechwaldon.users.api.employee;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import com.wojciechwaldon.users.api.UsersModuleHttpEndpoint;
import com.wojciechwaldon.users.domain.api.employee.Employee;
import com.wojciechwaldon.users.domain.api.employee.find.FindEmployeeQuery;
import com.wojciechwaldon.users.domain.api.employee.find.FindEmployeeQueryView;
import com.wojciechwaldon.users.domain.api.employee.save.SaveEmployeeCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/employee",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeHttpEndpoints extends UsersModuleHttpEndpoint {
    public EmployeeHttpEndpoints(CommandExecutor commandExecutor,
                                 QueryExecutor queryExecutor) {
        super(commandExecutor, queryExecutor);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void saveEmployee(@RequestBody Employee employee) throws Exception {
        SaveEmployeeCommand saveEmployeeCommand = SaveEmployeeCommand.of(employee);

        commandExecutor.execute(saveEmployeeCommand);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public FindEmployeeQueryView findEmployee(@PathVariable Long id) throws Exception {
        FindEmployeeQuery findEmployeeQuery = FindEmployeeQuery.of(id);

        return queryExecutor.execute(findEmployeeQuery);
    }
}
