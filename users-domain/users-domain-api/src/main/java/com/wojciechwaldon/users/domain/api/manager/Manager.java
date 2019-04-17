package com.wojciechwaldon.users.domain.api.manager;

import com.wojciechwaldon.users.domain.api.User;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Manager extends User {

    @NonNull
    private String password;

    @NonNull
    private String restaurantId;

    private Manager(
                    @NonNull String firstName,
                    @NonNull String lastName,
                    @NonNull String email,
                    @NonNull String telephone,
                    @NonNull String password,
                    @NonNull String restaurantId) {
        super(firstName, lastName, email, telephone);
        this.password = password;
        this.restaurantId = restaurantId;
    }

    public static Manager of(@NonNull String firstName,
                             @NonNull String lastName,
                             @NonNull String email,
                             @NonNull String telephone,
                             @NonNull String password,
                             @NonNull String restaurantId) {
        return new Manager(firstName, lastName, email, telephone, password, restaurantId);
    }
}
