package com.wojciechwaldon.users.infrastructure.delivery;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import com.wojciechwaldon.users.api.authorizeduser.AuthorizedUserHttpEndpoints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpEndpointsConfiguration {

    @Bean
    public AuthorizedUserHttpEndpoints authorizedUserHttpEndpoints(CommandExecutor commandExecutor,
                                                                   QueryExecutor queryExecutor) {
        return new AuthorizedUserHttpEndpoints(commandExecutor, queryExecutor);
    }
}
