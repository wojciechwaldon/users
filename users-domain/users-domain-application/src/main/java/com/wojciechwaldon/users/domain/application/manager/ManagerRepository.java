package com.wojciechwaldon.users.domain.application.manager;

import com.wojciechwaldon.users.domain.api.manager.Manager;

import java.util.Optional;

public interface ManagerRepository {

    void saveManager(Manager manager);

    Optional<Manager> findManager(Long id);
}
