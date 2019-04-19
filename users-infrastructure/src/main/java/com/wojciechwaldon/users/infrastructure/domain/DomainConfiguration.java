package com.wojciechwaldon.users.infrastructure.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DomainJpaConfiguration.class, DomainCommandHandlersConfiguration.class})
public class DomainConfiguration {
}
