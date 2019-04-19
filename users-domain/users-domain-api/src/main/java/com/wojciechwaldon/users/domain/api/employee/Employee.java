package com.wojciechwaldon.users.domain.api.employee;

import com.wojciechwaldon.users.domain.api.Token;
import com.wojciechwaldon.users.domain.api.User;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Entity;

@Entity
@Getter
public class Employee extends User {

    @NonNull
    private String password;

    @NonNull
    private String restaurantId;

    private Employee(Long id,
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

    public static Employee of(Long id,
                              Token token,
                              String firstName,
                              String lastName,
                              String email,
                              String telephone,
                              String password,
                              String restaurantId) {
        return new Employee(id, token, firstName, lastName, email, telephone, password, restaurantId);
    }
}
