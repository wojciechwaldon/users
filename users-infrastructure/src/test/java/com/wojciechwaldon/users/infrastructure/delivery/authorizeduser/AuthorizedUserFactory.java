package com.wojciechwaldon.users.infrastructure.delivery.authorizeduser;

import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;

class AuthorizedUserFactory {

    static AuthorizedUser build() {
        return AuthorizedUser.of(
                "Role",
                "First name",
                "Last name",
                "email",
                "telephone");
    }
}
