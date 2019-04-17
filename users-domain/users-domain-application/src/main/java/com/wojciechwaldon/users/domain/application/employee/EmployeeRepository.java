package com.wojciechwaldon.users.domain.application.employee;

import com.wojciechwaldon.users.domain.api.employee.Employee;

import java.util.Optional;

public interface EmployeeRepository {

    void saveEmployee(Employee employee);

    Optional<Employee> findEmployee(Long id);
}
