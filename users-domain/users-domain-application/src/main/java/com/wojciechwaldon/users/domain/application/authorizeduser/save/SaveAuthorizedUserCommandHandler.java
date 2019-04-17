package com.wojciechwaldon.users.domain.application.authorizeduser.save;

import com.wojciechwaldon.cqrs.api.command.CommandHandler;
import com.wojciechwaldon.users.domain.api.authorizeduser.save.SaveAuthorizedUserCommand;
import com.wojciechwaldon.users.domain.application.authorizeduser.AuthorizedUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class SaveAuthorizedUserCommandHandler implements CommandHandler<SaveAuthorizedUserCommand> {

    private AuthorizedUserRepository authorizedUserRepository;

    @Transactional
    @Override
    public void handle(final SaveAuthorizedUserCommand saveAuthorizedUserCommand) throws Exception {
        authorizedUserRepository.saveAuthorizedUser(saveAuthorizedUserCommand.getAuthorizedUser());
    }
}
