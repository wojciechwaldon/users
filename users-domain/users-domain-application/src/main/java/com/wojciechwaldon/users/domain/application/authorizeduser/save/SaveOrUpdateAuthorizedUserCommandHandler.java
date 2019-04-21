package com.wojciechwaldon.users.domain.application.authorizeduser.save;

import com.wojciechwaldon.cqrs.api.command.CommandHandler;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.domain.api.authorizeduser.save.SaveOrUpdateAuthorizedUserCommand;
import com.wojciechwaldon.users.domain.application.authorizeduser.AuthorizedUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
public class SaveOrUpdateAuthorizedUserCommandHandler implements CommandHandler<SaveOrUpdateAuthorizedUserCommand> {

    private AuthorizedUserRepository repository;

    @Transactional
    @Override
    public void handle(final SaveOrUpdateAuthorizedUserCommand saveOrUpdateAuthorizedUserCommand) throws Exception {
        AuthorizedUser authorizedUserFromCommand = saveOrUpdateAuthorizedUserCommand.getAuthorizedUser();
        Optional<AuthorizedUser> optionalAuthorizedUser = repository.findAuthorizedUserByEmail(authorizedUserFromCommand.getEmail());

        final AuthorizedUser authorizedUser = optionalAuthorizedUser
                .map(a -> updateAuthorizedUser(a, authorizedUserFromCommand))
                .orElse(authorizedUserFromCommand);

        repository.saveAuthorizedUser(authorizedUser);
    }

    private AuthorizedUser updateAuthorizedUser(AuthorizedUser oldAuthorizedUser, AuthorizedUser newAuthorizedUser) {
        return AuthorizedUser.of(oldAuthorizedUser.getId(),
                oldAuthorizedUser.getRole(),
                newAuthorizedUser.getToken(),
                newAuthorizedUser.getFirstName(),
                newAuthorizedUser.getLastName(),
                newAuthorizedUser.getEmail(),
                newAuthorizedUser.getTelephone());
    }

}
