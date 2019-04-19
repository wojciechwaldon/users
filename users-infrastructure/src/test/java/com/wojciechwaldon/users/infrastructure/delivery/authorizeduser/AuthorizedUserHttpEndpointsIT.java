package com.wojciechwaldon.users.infrastructure.delivery.authorizeduser;


import com.wojciechwaldon.commons.JsonConverter;
import com.wojciechwaldon.cqrs.infrastructure.CqrsConfiguration;
import com.wojciechwaldon.users.domain.api.authorizeduser.AuthorizedUser;
import com.wojciechwaldon.users.infrastructure.UsersModuleConfiguration;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(classes = {UsersModuleConfiguration.class, CqrsConfiguration.class})
@RequiredArgsConstructor
public class AuthorizedUserHttpEndpointsIT {

    @Autowired
    private MockMvc mockMvc;

    @Spy
    private JsonConverter jsonConverter;

    @Test
    public void shouldSaveAuthorizedUser() throws Exception {
        AuthorizedUser authorizedUser = AuthorizedUser.of(
                "First name",
                "Last name",
                "email",
                "telephone");

        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(jsonConverter.convert(authorizedUser)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFindAuthorizedUser() throws Exception {
        this.mockMvc.perform(get("/user/1"))
                .andDo(print()).andExpect(status().isOk());
    }
}
