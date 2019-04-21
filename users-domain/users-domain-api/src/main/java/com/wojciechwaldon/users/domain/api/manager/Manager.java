package com.wojciechwaldon.users.domain.api.manager;

import com.wojciechwaldon.users.domain.api.Token;
import com.wojciechwaldon.users.domain.api.employee.Employee;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGER", schema = "USERS")
@Getter
@EqualsAndHashCode(callSuper = false)
public class Manager extends Employee {

    private Manager() {
        super();
    }

    private Manager(Long id,
                    String role,
                    Token token,
                    String firstName,
                    String lastName,
                    String email,
                    String telephone,
                    String password,
                    Long restaurantId) {
        super(id, role, token, firstName, lastName, email, telephone, password, restaurantId);
    }

    private Manager(String role,
                    String firstName,
                    String lastName,
                    String email,
                    String telephone,
                    String password,
                    Long restaurantId) {
        super(role, firstName, lastName, email, telephone, password, restaurantId);
    }

    public static Manager of(Long id,
                             String role,
                             Token token,
                             String firstName,
                             String lastName,
                             String email,
                             String telephone,
                             String password,
                             Long restaurantId) {
        return new Manager(id, role, token, firstName, lastName, email, telephone, password, restaurantId);
    }

    public static Manager of(String role,
                             String firstName,
                             String lastName,
                             String email,
                             String telephone,
                             String password,
                             Long restaurantId) {
        return new Manager(role, firstName, lastName, email, telephone, password, restaurantId);
    }
}
