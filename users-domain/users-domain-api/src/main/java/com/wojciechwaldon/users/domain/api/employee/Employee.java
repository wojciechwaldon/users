package com.wojciechwaldon.users.domain.api.employee;

import com.wojciechwaldon.users.domain.api.Token;
import com.wojciechwaldon.users.domain.api.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EMPLOYEE", schema = "USERS")
@Getter
@EqualsAndHashCode(callSuper = false)
public class Employee extends User {

    @NonNull
    private String password;

    @NotNull
    @Column(name = "RESTAURANT_ID")
    private Long restaurantId;

    protected Employee() {
        super();
    }

    protected Employee(String role,
                       String firstName,
                       String lastName,
                       String email,
                       String telephone,
                       String password,
                       Long restaurantId) {
        super(role, firstName, lastName, email, telephone);
        this.password = password;
        this.restaurantId = restaurantId;
    }

    protected Employee(Long id,
                       String role,
                       Token token,
                       String firstName,
                       String lastName,
                       String email,
                       String telephone,
                       String password,
                       Long restaurantId) {
        super(id, role, token, firstName, lastName, email, telephone);
        this.password = password;
        this.restaurantId = restaurantId;
    }

    public static Employee of(String role,
                              String firstName,
                              String lastName,
                              String email,
                              String telephone,
                              String password,
                              Long restaurantId) {
        return new Employee(role, firstName, lastName, email, telephone, password, restaurantId);
    }

    public static Employee of(Long id,
                              String role,
                              Token token,
                              String firstName,
                              String lastName,
                              String email,
                              String telephone,
                              String password,
                              Long restaurantId) {
        return new Employee(id, role, token, firstName, lastName, email, telephone, password, restaurantId);
    }
}
