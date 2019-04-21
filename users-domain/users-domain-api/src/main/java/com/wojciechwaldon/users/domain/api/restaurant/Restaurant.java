package com.wojciechwaldon.users.domain.api.restaurant;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "RESTAURANT", schema = "USERS")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS.RESTAURANT_SEQUENCE")
    private Long id;

    @NotNull
    private String name;

    private Restaurant(String name) {
        this.name = name;
    }

    public static Restaurant of(String name) {
        return new Restaurant(name);
    }
}
