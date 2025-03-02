package github.devparkge.realworld.controller.user.api;

import github.devparkge.realworld.controller.user.model.request.UpdateUserRequest;
import github.devparkge.realworld.domain.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@DisplayName("UserApiController 통합 테스트")
class UserApiControllerTest extends IntegrationTest {

    @Test
    @DisplayName("GET /api/user - 인증된 유저의 정보를 반환한다.")
    void currentUser() throws Exception {
        var user = createUser(
                "parkge",
                "parkge@gmail.com",
                "1234"
        );
        String token = "Bearer " + jwtUtil.generateToken(user.uuid());
        mockMvc.perform(get("/api/user")
                        .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(jsonPath("$.user.email").value(user.email()))
                .andExpect(jsonPath("$.user.username").value(user.username()))
                .andExpect(jsonPath("$.user.token").isNotEmpty())
                .andExpect(jsonPath("$.user.bio").doesNotExist())
                .andExpect(jsonPath("$.user.image").doesNotExist())
                .andDo(print());
    }

    @Test
    @DisplayName("PUT /api/user - 정보를 수정한 유저의 정보를 반환한다.")
    void updateUser() throws Exception {
        User user = createUser(
                "parkge",
                "parkge@gmail.com",
                "1234"
        );
        var request = new UpdateUserRequest(
                "parkge1@gmail",
                null,
                null,
                "I like to skateboard",
                "https://i.stack.imgur.com/xHWG8.jpg"
        );
        String token = "Bearer " + jwtUtil.generateToken(user.uuid());
        mockMvc.perform(put("/api/user")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("user", request))))
                .andExpect(jsonPath("$.user.email").value(request.email()))
                .andExpect(jsonPath("$.user.username").value(user.username()))
                .andExpect(jsonPath("$.user.bio").value(request.bio()))
                .andExpect(jsonPath("$.user.image").value(request.image()))
                .andDo(print());
    }
}