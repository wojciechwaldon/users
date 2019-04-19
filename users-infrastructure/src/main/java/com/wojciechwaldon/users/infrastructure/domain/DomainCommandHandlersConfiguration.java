package com.wojciechwaldon.users.infrastructure.domain;

import com.wojciechwaldon.users.domain.application.authorizeduser.AuthorizedUserRepository;
import com.wojciechwaldon.users.domain.application.authorizeduser.find.FindAuthorizedUserQueryHandler;
import com.wojciechwaldon.users.domain.application.authorizeduser.save.SaveAuthorizedUserCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DomainCommandHandlersConfiguration {

    @Bean
    public SaveAuthorizedUserCommandHandler saveAuthorizedUserCommandHandler(AuthorizedUserRepository repository) {
        return new SaveAuthorizedUserCommandHandler(repository);
    }

    @Bean
    public FindAuthorizedUserQueryHandler findAuthorizedUserQueryHandler(AuthorizedUserRepository repository) {
        return new FindAuthorizedUserQueryHandler(repository);
    }
}
