package com.wojciechwaldon.users.infrastructure.delivery.manager;


import com.wojciechwaldon.commons.json.JsonToObjectConverter;
import com.wojciechwaldon.commons.json.ObjectToJsonConverter;
import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.infrastructure.CqrsConfiguration;
import com.wojciechwaldon.users.domain.api.Token;
import com.wojciechwaldon.users.domain.api.manager.Manager;
import com.wojciechwaldon.users.domain.api.manager.save.SaveManagerCommand;
import com.wojciechwaldon.users.infrastructure.UsersModuleConfiguration;
import com.wojciechwaldon.users.infrastructure.common.TestTokenGenerator;
import com.wojciechwaldon.users.infrastructure.repository.manager.ManagerRepositoryImpl;
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
public class ManagerHttpEndpointsIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private ManagerRepositoryImpl managerRepository;

    @Spy
    private JsonToObjectConverter<Manager> managerJsonToObjectConverter;

    @Spy
    private ObjectToJsonConverter objectToJsonConverter;

    @Before
    public void cleanUp() {
        managerRepository.deleteAll();
    }

    @Test
    public void shouldSaveManager() throws Exception {
        Manager manager = ManagerFactory.build();
        this.mockMvc.perform(post("/manager")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectToJsonConverter.convert(manager)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFindManager() throws Exception {
        //given
        Manager manager = ManagerFactory.build();

        //when
        commandExecutor.execute(SaveManagerCommand.of(manager));

        //then
        ResultActions resultActions = this.mockMvc.perform(get("/manager/".concat(manager.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        assertManagerResult(manager, resultActions);
    }

    @Test
    public void shouldUpdateManagerWithToken() throws Exception {
        //given
        Manager manager = ManagerFactory.build();

        //when
        commandExecutor.execute(SaveManagerCommand.of(manager));

        Token token = Token.of(TestTokenGenerator.generate(),
                LocalDateTime.now().plusMinutes(15));

        manager.withToken(token);
        commandExecutor.execute(SaveManagerCommand.of(manager));

        //then
        ResultActions resultActions = this.mockMvc.perform(get("/manager/".concat(manager.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        assertManagerResult(manager, resultActions);
    }

    private void assertManagerResult(Manager manager, ResultActions resultActions) throws UnsupportedEncodingException {
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Manager managerResult = managerJsonToObjectConverter.convert(contentAsString, Manager.class);

        assertEquals(manager, managerResult);
    }
}
