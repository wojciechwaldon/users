package com.wojciechwaldon.users.infrastructure.delivery;

import com.wojciechwaldon.users.api.authorizeduser.AuthorizedUserHttpEndpoints;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpEndpointsConfiguration {

    @Bean
    public AuthorizedUserHttpEndpoints authorizedUserHttpEndpoints(AuthorizedUserRepository<AuthorizedUser> authorizedUserRepository) {
        return new AuthorizedUserHttpEndpoints(authorizedUserRepository);
    }
}
