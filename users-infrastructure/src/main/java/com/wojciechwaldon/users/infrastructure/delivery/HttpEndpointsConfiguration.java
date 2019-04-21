package com.wojciechwaldon.users.infrastructure.delivery;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.api.command.CommandHandler;
import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import com.wojciechwaldon.cqrs.api.query.QueryHandler;
import com.wojciechwaldon.users.api.authorizeduser.AuthorizedUserHttpEndpoints;
import com.wojciechwaldon.users.api.employee.EmployeeHttpEndpoints;
import com.wojciechwaldon.users.api.manager.ManagerHttpEndpoints;
import com.wojciechwaldon.users.api.restaurant.RestaurantHttpEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpEndpointsConfiguration {

    @Bean
    public AuthorizedUserHttpEndpoints authorizedUserHttpEndpoints(CommandExecutor commandExecutor,
                                                                   QueryExecutor queryExecutor) {
        return new AuthorizedUserHttpEndpoints(commandExecutor, queryExecutor);
    }

    @Bean
    public EmployeeHttpEndpoints employeeHttpEndpoints(CommandExecutor commandExecutor,
                                                       QueryExecutor queryExecutor) {
        return new EmployeeHttpEndpoints(commandExecutor, queryExecutor);
    }

    @Bean
    public ManagerHttpEndpoints managerHttpEndpoints(CommandExecutor commandExecutor,
                                                     QueryExecutor queryExecutor) {
        return new ManagerHttpEndpoints(commandExecutor, queryExecutor);
    }

    @Bean
    public RestaurantHttpEndpoint restaurantHttpEndpoint(CommandExecutor commandExecutor,
                                                         QueryExecutor queryExecutor) {
        return new RestaurantHttpEndpoint(commandExecutor, queryExecutor);
    }
}
