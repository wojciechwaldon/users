package com.wojciechwaldon.users.infrastructure.delivery.employee;

import com.wojciechwaldon.users.domain.api.employee.Employee;

class EmployeeFactory {

    static Employee build() {
        return Employee.of(
                "Role",
                "First name",
                "Last name",
                "email",
                "telephone",
                "password",
                1L);
    }
}
