package com.wojciechwaldon.users.infrastructure.delivery.authorizeduser;


import com.wojciechwaldon.commons.json.JsonToObjectConverter;
import com.wojciechwaldon.commons.json.ObjectToJsonConverter;
import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.cqrs.infrastructure.CqrsConfiguration;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.domain.api.authorizeduser.save.SaveAuthorizedUserCommand;
import com.wojciechwaldon.users.domain.api.token.Token;
import com.wojciechwaldon.users.infrastructure.UsersModuleConfiguration;
import com.wojciechwaldon.users.infrastructure.common.TestTokenGenerator;
import com.wojciechwaldon.users.infrastructure.repository.authorizeduser.AuthorizedUserRepositoryImpl;
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
public class AuthorizedUserHttpEndpointsIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private AuthorizedUserRepositoryImpl authorizedUserRepository;

    @Spy
    private JsonToObjectConverter<AuthorizedUser> authorizedUserJsonToObjectConverter;

    @Spy
    private ObjectToJsonConverter objectToJsonConverter;

    @Before
    public void cleanUp() {
        authorizedUserRepository.deleteAll();
    }

    @Test
    public void shouldSaveAuthorizedUser() throws Exception {
        //given
        AuthorizedUser authorizedUser = AuthorizedUserFactory.build();

        //then
        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectToJsonConverter.convert(authorizedUser)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateAuthorizedUser() throws Exception {
        //given
        AuthorizedUser authorizedUser = AuthorizedUserFactory.buildWithToken();

        //when
        commandExecutor.execute(SaveAuthorizedUserCommand.of(authorizedUser));

        authorizedUser.withToken(Token.of(TestTokenGenerator.generate(),
                LocalDateTime.now().plusMinutes(15)));

        //then
        this.mockMvc.perform(post("/user/update")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectToJsonConverter.convert(authorizedUser)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFindAuthorizedUser() throws Exception {
        //given
        AuthorizedUser authorizedUser = AuthorizedUserFactory.build();

        //when
        commandExecutor.execute(SaveAuthorizedUserCommand.of(authorizedUser));

        //then
        ResultActions resultActions = this.mockMvc.perform(get("/user/".concat(authorizedUser.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        assertAuthorizedUserResult(authorizedUser, resultActions);
    }

    @Test
    public void shouldUpdateUserWithToken() throws Exception {
        //given
        AuthorizedUser authorizedUser = AuthorizedUserFactory.build();

        //when
        commandExecutor.execute(SaveAuthorizedUserCommand.of(authorizedUser));

        Token token = Token.of(TestTokenGenerator.generate(),
                LocalDateTime.now().plusMinutes(15));

        authorizedUser.withToken(token);
        commandExecutor.execute(SaveAuthorizedUserCommand.of(authorizedUser));

        //then
        ResultActions resultActions = this.mockMvc.perform(get("/user/".concat(authorizedUser.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        assertAuthorizedUserResult(authorizedUser, resultActions);
    }

    private void assertAuthorizedUserResult(AuthorizedUser authorizedUser, ResultActions resultActions) throws UnsupportedEncodingException {
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        AuthorizedUser authorizedUserResult = authorizedUserJsonToObjectConverter.convert(contentAsString, AuthorizedUser.class);

        assertEquals(authorizedUser, authorizedUserResult);
    }
}
