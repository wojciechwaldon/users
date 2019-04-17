package com.wojciechwaldon.users.infrastructure.repository.manager;

import com.wojciechwaldon.users.domain.api.manager.Manager;
import com.wojciechwaldon.users.domain.application.manager.ManagerRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepositoryImpl extends ManagerRepository,
        CrudRepository<Manager, Long> {

    default void saveManager(Manager manager) {
        save(manager);
    }

    default Optional<Manager> findManager(Long id) {
        return findById(id);
    }
}
