package com.wojciechwaldon.users.domain.api;
;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Token {

    @Id
    @Column(unique = true)
    @NotNull
    private String value;

    @NotNull
    private LocalDateTime expiratiDateTime;
}
