package com.wojciechwaldon.users.infrastructure.repository.employee;

import com.wojciechwaldon.users.domain.api.employee.Employee;
import com.wojciechwaldon.users.domain.application.employee.EmployeeRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepositoryImpl extends EmployeeRepository, CrudRepository<Employee, Long> {

    default void saveEmployee(Employee employee) {
        save(employee);
    }

    default Optional<Employee> findEmployee(Long id) {
        return findById(id);
    }
}
