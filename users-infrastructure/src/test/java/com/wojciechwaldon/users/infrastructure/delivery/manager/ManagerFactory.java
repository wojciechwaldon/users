package com.wojciechwaldon.users.infrastructure.delivery.manager;

import com.wojciechwaldon.users.domain.api.manager.Manager;

class ManagerFactory {

    static Manager build() {
        return Manager.of(
                "Role",
                "First name",
                "Last name",
                "email",
                "telephone",
                "password",
                1L);
    }
}
