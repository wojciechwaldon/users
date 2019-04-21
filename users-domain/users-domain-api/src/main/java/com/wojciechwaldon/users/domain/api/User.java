package com.wojciechwaldon.users.domain.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
abstract public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS.USERS_SEQUENCE")
    private Long id;

    @NotNull
    private String role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "TOKEN_ID")
    protected Token token;

    @NotNull
    @Column(name = "FIRST_NAME")
    protected String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    protected String lastName;

    @NotNull
    protected String email;

    @NotNull
    protected String telephone;

    protected User(String role, String firstName, String lastName, String email, String telephone) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
    }

    public void withToken(Token token) {
        this.token = token;
    }
}
