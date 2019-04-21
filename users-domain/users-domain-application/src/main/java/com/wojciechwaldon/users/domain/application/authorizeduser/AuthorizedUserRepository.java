package com.wojciechwaldon.users.domain.application.authorizeduser;//package com.wojciechwaldon.users.domain.api.authorizeduser;


import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;

import java.util.Optional;

public interface AuthorizedUserRepository {

    void saveAuthorizedUser(AuthorizedUser authorizedUser);

    Optional<AuthorizedUser> findAuthorizedUser(Long id);

    Optional<AuthorizedUser> findAuthorizedUserByEmail(String email);
}
