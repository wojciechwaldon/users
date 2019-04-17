package com.wojciechwaldon.users.infrastructure;

import com.wojciechwaldon.users.infrastructure.delivery.HttpEndpointsConfiguration;
import com.wojciechwaldon.users.infrastructure.domain.DomainConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        DomainConfiguration.class,
        HttpEndpointsConfiguration.class
})
public class UsersModuleConfiguration {
}
