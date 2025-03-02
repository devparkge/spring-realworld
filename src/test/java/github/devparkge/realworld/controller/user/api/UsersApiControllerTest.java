package github.devparkge.realworld.controller.user.api;

import github.devparkge.realworld.controller.user.model.request.LoginRequest;
import github.devparkge.realworld.controller.user.model.request.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("UsersApiController 통합 테스트")
class UsersApiControllerTest extends IntegrationTest {

    @Test
    @DisplayName("POST /api/users/login - 로그인된 유저의 정보를 반환한다.")
    void login() throws Exception {
        var user = createUser(
                "parkge",
                "parkge@gmail.com",
                "1234"
        );

        var requset = new LoginRequest(
                "parkge@gmail.com",
                "1234"
        );

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("user", requset))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email").value(user.email()))
                .andExpect(jsonPath("$.user.username").value(user.username()))
                .andExpect(jsonPath("$.user.token").isNotEmpty())
                .andExpect(jsonPath("$.user.bio").doesNotExist())
                .andExpect(jsonPath("$.user.image").doesNotExist())
                .andDo(print());
    }

    @Test
    @DisplayName("POST /api/users - 회원가입한 유저의 정보를 반환한다.")
    void signUp() throws Exception {
        var request = new SignUpRequest(
                "parkge",
                "parkge@gmail.com",
                "1234"
        );

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("user", request))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email").value(request.email()))
                .andExpect(jsonPath("$.user.username").value(request.username()))
                .andExpect(jsonPath("$.user.token").isNotEmpty())
                .andExpect(jsonPath("$.user.bio").doesNotExist())
                .andExpect(jsonPath("$.user.image").doesNotExist())
                .andDo(print());
    }
}