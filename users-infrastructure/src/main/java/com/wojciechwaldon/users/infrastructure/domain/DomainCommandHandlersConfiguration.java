package com.wojciechwaldon.users.infrastructure.domain;

import com.wojciechwaldon.users.domain.application.authorizeduser.AuthorizedUserRepository;
import com.wojciechwaldon.users.domain.application.authorizeduser.find.FindAuthorizedUserQueryHandler;
import com.wojciechwaldon.users.domain.application.authorizeduser.save.SaveAuthorizedUserCommandHandler;
import com.wojciechwaldon.users.domain.application.employee.EmployeeRepository;
import com.wojciechwaldon.users.domain.application.employee.find.FindEmployeeQueryHandler;
import com.wojciechwaldon.users.domain.application.employee.save.SaveEmployeeCommandHandler;
import com.wojciechwaldon.users.domain.application.manager.ManagerRepository;
import com.wojciechwaldon.users.domain.application.manager.find.FindManagerQueryHandler;
import com.wojciechwaldon.users.domain.application.manager.save.SaveManagerCommandHandler;
import com.wojciechwaldon.users.domain.application.restaurant.RestaurantRepository;
import com.wojciechwaldon.users.domain.application.restaurant.find.FindRestaurantQueryHandler;
import com.wojciechwaldon.users.domain.application.restaurant.save.SaveRestaurantCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DomainCommandHandlersConfiguration {

    @Bean
    public SaveAuthorizedUserCommandHandler saveAuthorizedUserCommandHandler(AuthorizedUserRepository repository) {
        return new SaveAuthorizedUserCommandHandler(repository);
    }

    @Bean
    public FindAuthorizedUserQueryHandler findAuthorizedUserQueryHandler(AuthorizedUserRepository repository) {
        return new FindAuthorizedUserQueryHandler(repository);
    }

    @Bean
    public SaveEmployeeCommandHandler saveEmployeeCommandHandler(EmployeeRepository repository) {
        return new SaveEmployeeCommandHandler(repository);
    }

    @Bean
    public FindEmployeeQueryHandler findEmployeeQueryHandler(EmployeeRepository repository) {
        return new FindEmployeeQueryHandler(repository);
    }

    @Bean
    public SaveManagerCommandHandler saveManagerCommandHandler(ManagerRepository repository) {
        return new SaveManagerCommandHandler(repository);
    }

    @Bean
    public FindManagerQueryHandler findManagerQueryHandler(ManagerRepository repository) {
        return new FindManagerQueryHandler(repository);
    }

    @Bean
    public FindRestaurantQueryHandler findRestaurantQueryHandler(RestaurantRepository repository) {
        return new FindRestaurantQueryHandler(repository);
    }

    @Bean
    public SaveRestaurantCommandHandler saveRestaurantCommandHandler(RestaurantRepository repository) {
        return new SaveRestaurantCommandHandler(repository);
    }
}
