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
                    Token token,
                    String firstName,
                    String lastName,
                    String email,
                    String telephone,
                    String password,
                    Long restaurantId) {
        super(id, token, firstName, lastName, email, telephone, password, restaurantId);
    }

    private Manager(String firstName,
                    String lastName,
                    String email,
                    String telephone,
                    String password,
                    Long restaurantId) {
        super(firstName, lastName, email, telephone, password, restaurantId);
    }

    public static Manager of(Long id,
                             Token token,
                             String firstName,
                             String lastName,
                             String email,
                             String telephone,
                             String password,
                             Long restaurantId) {
        return new Manager(id, token, firstName, lastName, email, telephone, password, restaurantId);
    }

    public static Manager of(String firstName,
                             String lastName,
                             String email,
                             String telephone,
                             String password,
                             Long restaurantId) {
        return new Manager(firstName, lastName, email, telephone, password, restaurantId);
    }
}
