package com.wojciechwaldon.users.domain.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Token {

    @Id
    @Column(unique = true, name = "TOKEN_VALUE")
    @NotNull
    private String value;

    @NotNull
    @Column(name = "EXPIRATION_DATETIME")
    private LocalDateTime expiratiDateTime;
}
