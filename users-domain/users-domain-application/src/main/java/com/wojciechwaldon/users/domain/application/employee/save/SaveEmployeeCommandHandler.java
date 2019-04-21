package com.wojciechwaldon.users.domain.application.employee.save;

import com.wojciechwaldon.cqrs.api.command.CommandHandler;
import com.wojciechwaldon.users.domain.api.employee.save.SaveEmployeeCommand;
import com.wojciechwaldon.users.domain.application.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class SaveEmployeeCommandHandler implements CommandHandler<SaveEmployeeCommand> {

    private EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public void handle(final SaveEmployeeCommand saveEmployeeCommand) throws Exception {
        employeeRepository.saveEmployee(saveEmployeeCommand.getEmployee());
    }
}
