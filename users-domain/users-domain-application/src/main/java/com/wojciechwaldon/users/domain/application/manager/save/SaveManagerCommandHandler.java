package com.wojciechwaldon.users.domain.application.manager.save;

import com.wojciechwaldon.cqrs.api.command.CommandHandler;
import com.wojciechwaldon.users.domain.api.manager.save.SaveManagerCommand;
import com.wojciechwaldon.users.domain.application.manager.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class SaveManagerCommandHandler implements CommandHandler<SaveManagerCommand> {

    private ManagerRepository managerRepository;

    @Transactional
    @Override
    public void handle(final SaveManagerCommand saveManagerCommand) throws Exception {
        managerRepository.saveManager(saveManagerCommand.getManager());
    }
}
