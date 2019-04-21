package com.wojciechwaldon.users.domain.application.manager.find;

import com.wojciechwaldon.cqrs.api.query.QueryHandler;
import com.wojciechwaldon.users.domain.api.manager.Manager;
import com.wojciechwaldon.users.domain.api.manager.find.FindManagerQuery;
import com.wojciechwaldon.users.domain.api.manager.find.FindManagerQueryView;
import com.wojciechwaldon.users.domain.application.manager.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class FindManagerQueryHandler implements QueryHandler<FindManagerQuery, FindManagerQueryView> {

    private ManagerRepository managerRepository;

    @Transactional
    @Override
    public FindManagerQueryView handle(final FindManagerQuery findManagerQuery) throws Exception {
        Optional<Manager> optionalManager = managerRepository.findManager(findManagerQuery.getId());

        final Manager manager = optionalManager.orElseThrow(() -> {
            log.error("Manager with id: {} not found", findManagerQuery.getId());
            return new IllegalArgumentException("Manager not found");
        });

        return FindManagerQueryView.of(manager.getId(),
                manager.getToken(),
                manager.getFirstName(),
                manager.getLastName(),
                manager.getEmail(),
                manager.getTelephone(),
                manager.getPassword(),
                manager.getRestaurantId());
    }
}
