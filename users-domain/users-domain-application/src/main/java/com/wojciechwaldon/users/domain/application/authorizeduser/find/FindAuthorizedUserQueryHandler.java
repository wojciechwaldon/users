package com.wojciechwaldon.users.domain.application.authorizeduser.find;

import com.wojciechwaldon.cqrs.api.query.QueryHandler;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.domain.api.authorizeduser.find.FindAuthorizedUserQuery;
import com.wojciechwaldon.users.domain.api.authorizeduser.find.FindAuthorizedUserQueryView;
import com.wojciechwaldon.users.domain.application.authorizeduser.AuthorizedUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class FindAuthorizedUserQueryHandler implements QueryHandler<FindAuthorizedUserQuery, FindAuthorizedUserQueryView> {

    private AuthorizedUserRepository authorizedUserRepository;

    @Transactional
    @Override
    public FindAuthorizedUserQueryView handle(final FindAuthorizedUserQuery findAuthorizedUserQuery) throws Exception {
        Optional<AuthorizedUser> optionalAuthorizedUser = authorizedUserRepository.findAuthorizedUser(findAuthorizedUserQuery.getId());

        final AuthorizedUser authorizedUser = optionalAuthorizedUser.orElseThrow(() -> {
            log.error("User with id: {} not found", findAuthorizedUserQuery.getId());
            return new IllegalArgumentException("User not found");
        });

        return FindAuthorizedUserQueryView.of(authorizedUser.getId(),
                authorizedUser.getToken(),
                authorizedUser.getFirstName(),
                authorizedUser.getLastName(),
                authorizedUser.getEmail(),
                authorizedUser.getTelephone());
    }
}
