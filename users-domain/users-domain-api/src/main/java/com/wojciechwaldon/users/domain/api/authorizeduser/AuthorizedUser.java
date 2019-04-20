package com.wojciechwaldon.users.domain.api.authorizeduser;

import com.wojciechwaldon.users.domain.api.Token;
import com.wojciechwaldon.users.domain.api.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORIZED_USER", schema = "USERS")
@Getter
@EqualsAndHashCode(callSuper = false)
public class AuthorizedUser extends User {

    private AuthorizedUser() {
        super();
    }

    public AuthorizedUser(String firstName, String lastName, String email, String telephone) {
        super(firstName, lastName, email, telephone);
    }

    public AuthorizedUser(Long id, Token token, String firstName, String lastName, String email, String telephone) {
        super(id, token, firstName, lastName, email, telephone);
    }

    public static AuthorizedUser of(String firstName, String lastName, String email, String telephone) {
        return new AuthorizedUser(firstName, lastName, email, telephone);
    }

    public static AuthorizedUser of(Long id, Token token, String firstName, String lastName, String email, String telephone) {
        return new AuthorizedUser(id, token, firstName, lastName, email, telephone);
    }

    public void withToken(Token token) {
        super.token = token;
    }
}
