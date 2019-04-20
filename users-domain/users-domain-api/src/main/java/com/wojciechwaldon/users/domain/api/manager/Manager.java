package com.wojciechwaldon.users.domain.api.manager;

import com.wojciechwaldon.users.domain.api.Token;
import com.wojciechwaldon.users.domain.api.User;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGER", schema = "USERS")
@Getter
public class Manager extends User {

    @NonNull
    private String password;

    @NonNull
    private String restaurantId;

    private Manager() {
        super();
    }

    private Manager(Long id,
                    Token token,
                    String firstName,
                    String lastName,
                    String email,
                    String telephone,
                    String password,
                    String restaurantId) {
        super(id, token, firstName, lastName, email, telephone);
        this.password = password;
        this.restaurantId = restaurantId;
    }

    public static Manager of(Long id,
                             Token token,
                             String firstName,
                             String lastName,
                             String email,
                             String telephone,
                             String password,
                             String restaurantId) {
        return new Manager(id, token, firstName, lastName, email, telephone, password, restaurantId);
    }
}
