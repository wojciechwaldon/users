package com.wojciechwaldon.users.domain.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TOKEN", schema = "USERS")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Token implements Serializable {

    @Id
    @Column(unique = true, name = "TOKEN_VALUE")
    @NotNull
    private String value;

    @NotNull
    @Column(name = "EXPIRATION_DATETIME")
    private LocalDateTime expiratiDateTime;
}
