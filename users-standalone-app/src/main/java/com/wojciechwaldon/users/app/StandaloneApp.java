package com.wojciechwaldon.users.app;

import com.wojciechwaldon.users.api.cqrs.command.*;
import com.wojciechwaldon.users.api.cqrs.example.GetUserDataQuery;
import com.wojciechwaldon.users.api.cqrs.example.GetUserDataQueryView;
import com.wojciechwaldon.users.api.cqrs.query.Query;
import com.wojciechwaldon.users.api.cqrs.query.QueryExecutor;
import com.wojciechwaldon.users.api.cqrs.query.QueryView;
import com.wojciechwaldon.users.infrastructure.UsersModuleConfiguration;
import lombok.val;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Import({
        UsersModuleConfiguration.class
})
public class StandaloneApp {

    public static void main(String[] args) {
        SpringApplication.run(StandaloneApp.class, args);
    }

    @Bean
    public QueryExecutor queryExecutor() {
        return new QueryExecutor() {
            public QueryView execute(Query query) {
                System.out.println("Query: " + query.toString());
                return GetUserDataQueryView.of("Wojtek", "Waldon");
            }
        };
    }

    @Bean
    public SaveUserCommandHandler saveUserCommandHandler() {
        return new SaveUserCommandHandler();
    }

    @Bean
    @DependsOn("saveUserCommandHandler")
    public HandlersProvider handlersProvider(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        return new SpringHandlersProvider(configurableListableBeanFactory);
    }

    @Bean
    @DependsOn("handlersProvider")
    public CommandExecutor commandExecutor(HandlersProvider handlersProvider) {
        return new CustomCommandExecutor(handlersProvider);
    }

    @Bean
    @DependsOn({"saveUserCommandHandler", "handlersProvider", "commandExecutor"})
    public String test(CommandExecutor commandExecutor) {
        SaveUserCommand saveUserCommand = SaveUserCommand.of("name");

   //     commandExecutor.execute(saveUserCommand);
//        GetUserDataQueryView of = GetUserDataQueryView.of("Wojtek", "Waldon");
//        GetUserDataQuery query = GetUserDataQuery.of("id");
//
//        GetUserDataQueryView queryView = queryExecutor().execute(query);

        return "XD";
    }

}
