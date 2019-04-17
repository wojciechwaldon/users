package com.wojciechwaldon.users.infrastructure.repository.authorizeduser;

import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.domain.application.authorizeduser.AuthorizedUserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizedUserRepositoryImpl extends AuthorizedUserRepository, CrudRepository<AuthorizedUser, Long> {

    default void saveAuthorizedUser(AuthorizedUser authorizedUser) {
        save(authorizedUser);
    }

    default Optional<AuthorizedUser> findAuthorizedUser(Long id) {
        return findById(id);
    }
}
