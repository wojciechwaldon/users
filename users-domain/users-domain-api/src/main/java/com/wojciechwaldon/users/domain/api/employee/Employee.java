package com.wojciechwaldon.users.domain.api.employee;

import com.wojciechwaldon.users.domain.api.User;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Employee extends User {

    @NonNull
    private String password;

    @NonNull
    private String restaurantId;

    private Employee(@NonNull String firstName,
                     @NonNull String lastName,
                     @NonNull String email,
                     @NonNull String telephone,
                     @NonNull String password,
                     @NonNull String restaurantId) {
        super(firstName, lastName, email, telephone);
        this.password = password;
        this.restaurantId = restaurantId;
    }

    public static Employee of(@NonNull String firstName,
                              @NonNull String lastName,
                              @NonNull String email,
                              @NonNull String telephone,
                              @NonNull String password,
                              @NonNull String restaurantId) {
        return new Employee(firstName, lastName, email, telephone, password, restaurantId);
    }
}
