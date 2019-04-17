package com.wojciechwaldon.users.domain.api.authorizeduser;

import com.wojciechwaldon.users.domain.api.Token;
import com.wojciechwaldon.users.domain.api.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizedUser extends User {

//    public AuthorizedUser(String firstName, String lastName, String email, String telephone) {
//        super(firstName, lastName, email, telephone);
//    }

    public AuthorizedUser(Long id, Token token, String firstName, String lastName, String email, String telephone) {
        super(id, token, firstName, lastName, email, telephone);
    }

//    public static AuthorizedUser of(String firstName, String lastName, String email, String telephone) {
//        return new AuthorizedUser(firstName, lastName, email, telephone);
//    }

    public static AuthorizedUser of(Long id, Token token, String firstName, String lastName, String email, String telephone) {
        return new AuthorizedUser(id, token, firstName, lastName, email, telephone);
    }
}
