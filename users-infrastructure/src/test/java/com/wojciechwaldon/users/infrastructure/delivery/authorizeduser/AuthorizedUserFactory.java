package com.wojciechwaldon.users.infrastructure.delivery.authorizeduser;

import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.domain.api.token.Token;
import com.wojciechwaldon.users.infrastructure.common.TestTokenGenerator;

import java.time.LocalDateTime;

class AuthorizedUserFactory {

    static AuthorizedUser build() {
        return AuthorizedUser.of(
                "Role",
                "First name",
                "Last name",
                "email",
                "telephone");
    }

    static AuthorizedUser buildWithToken() {
        final Token token = Token.of(TestTokenGenerator.generate(), LocalDateTime.now().plusMinutes(15));
        return AuthorizedUser.of(
                "Role",
                token,
                "First name",
                "Last name",
                "email",
                "telephone");
    }
}
