package com.wojciechwaldon.users.infrastructure.delivery.employee;


import com.wojciechwaldon.commons.json.JsonToObjectConverter;
import com.wojciechwaldon.commons.json.ObjectToJsonConverter;
import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.infrastructure.CqrsConfiguration;
import com.wojciechwaldon.users.domain.api.token.Token;
import com.wojciechwaldon.users.domain.api.employee.Employee;
import com.wojciechwaldon.users.domain.api.employee.save.SaveEmployeeCommand;
import com.wojciechwaldon.users.infrastructure.UsersModuleConfiguration;
import com.wojciechwaldon.users.infrastructure.common.TestTokenGenerator;
import com.wojciechwaldon.users.infrastructure.repository.employee.EmployeeRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(classes = {UsersModuleConfiguration.class, CqrsConfiguration.class})
public class EmployeeHttpEndpointsIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private EmployeeRepositoryImpl employeeRepository;

    @Spy
    private JsonToObjectConverter<Employee> employeeJsonToObjectConverter;

    @Spy
    private ObjectToJsonConverter objectToJsonConverter;

    @Before
    public void cleanUp() {
        employeeRepository.deleteAll();
    }

    @Test
    public void shouldSaveEmployee() throws Exception {
        Employee employee = EmployeeFactory.build();
        this.mockMvc.perform(post("/employee")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectToJsonConverter.convert(employee)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFindEmployee() throws Exception {
        //given
        Employee employee = EmployeeFactory.build();

        //when
        commandExecutor.execute(SaveEmployeeCommand.of(employee));

        //then
        ResultActions resultActions = this.mockMvc.perform(get("/employee/".concat(employee.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        assertEmployeeResult(employee, resultActions);
    }

    @Test
    public void shouldUpdateEmployeeWithToken() throws Exception {
        //given
        Employee employee = EmployeeFactory.build();

        //when
        commandExecutor.execute(SaveEmployeeCommand.of(employee));

        Token token = Token.of(TestTokenGenerator.generate(),
                LocalDateTime.now().plusMinutes(15));

        employee.withToken(token);
        commandExecutor.execute(SaveEmployeeCommand.of(employee));

        //then
        ResultActions resultActions = this.mockMvc.perform(get("/employee/".concat(employee.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        assertEmployeeResult(employee, resultActions);
    }

    private void assertEmployeeResult(Employee employee, ResultActions resultActions) throws UnsupportedEncodingException {
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Employee employeeResult = employeeJsonToObjectConverter.convert(contentAsString, Employee.class);

        assertEquals(employee, employeeResult);
    }
}
