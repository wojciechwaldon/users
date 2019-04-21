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

    private AuthorizedUser(String role, String firstName, String lastName, String email, String telephone) {
        super(role, firstName, lastName, email, telephone);
    }

    private AuthorizedUser(Long id, String role, Token token, String firstName, String lastName, String email, String telephone) {
        super(id, role, token, firstName, lastName, email, telephone);
    }

    public static AuthorizedUser of(String role, String firstName, String lastName, String email, String telephone) {
        return new AuthorizedUser(role, firstName, lastName, email, telephone);
    }

    public static AuthorizedUser of(Long id, String role, Token token, String firstName, String lastName, String email, String telephone) {
        return new AuthorizedUser(id, role, token, firstName, lastName, email, telephone);
    }
}
