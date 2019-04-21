package com.wojciechwaldon.users.domain.application.employee.find;

import com.wojciechwaldon.cqrs.api.query.QueryHandler;
import com.wojciechwaldon.users.domain.api.employee.Employee;
import com.wojciechwaldon.users.domain.api.employee.find.FindEmployeeQuery;
import com.wojciechwaldon.users.domain.api.employee.find.FindEmployeeQueryView;
import com.wojciechwaldon.users.domain.application.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class FindEmployeeQueryHandler implements QueryHandler<FindEmployeeQuery, FindEmployeeQueryView> {

    private EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public FindEmployeeQueryView handle(final FindEmployeeQuery findEmployeeQuery) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployee(findEmployeeQuery.getId());

        final Employee employee = optionalEmployee.orElseThrow(() -> {
            log.error("Employee with id: {} not found", findEmployeeQuery.getId());
            return new IllegalArgumentException("Employee not found");
        });

        return FindEmployeeQueryView.of(employee.getId(),
                employee.getToken(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getTelephone(),
                employee.getPassword(),
                employee.getRestaurantId());
    }
}
