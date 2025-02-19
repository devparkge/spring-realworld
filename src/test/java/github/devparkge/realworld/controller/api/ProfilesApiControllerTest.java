package github.devparkge.realworld.controller.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@DisplayName("ProfilesApiController 통합 테스트")
class ProfilesApiControllerTest extends IntegrationTest {
    @Nested
    @DisplayName("GET /api/profiles/:username")
    class GetProfile {
        @Test
        @DisplayName("인증된 유저의 요청에 대한 username의 프로필을 반환")
        void authenticGetProfilse() throws Exception {
            var targetUser = createUser(
                    "gunKim",
                    "gunkim@gamil.com",
                    "1234"
            );
            var myUser = createUser(
                    "parkge",
                    "parkge@gamil.com",
                    "1234"
            );
            createFollower(
                    targetUser.username(),
                    myUser.uuid()
            );

            String token = "Bearer " + jwtUtil.generateToken(myUser.uuid());
            mockMvc.perform(get("/api/profiles/gunKim")
                            .header(HttpHeaders.AUTHORIZATION, token))
                    .andExpect(jsonPath("$.profile.username").value(targetUser.username()))
                    .andExpect(jsonPath("$.profile.bio").isEmpty())
                    .andExpect(jsonPath("$.profile.image").isEmpty())
                    .andExpect(jsonPath("$.profile.following").value(true))
                    .andDo(print());
        }

        @Test
        @DisplayName("인증되지 않은 유저의 요청에 대한 username의 프로필을 반환")
        void noGetProfilse() throws Exception {
            var targetUser = createUser(
                    "gunKim",
                    "gunkim@gamil.com",
                    "1234"
            );
            var myUser = createUser(
                    "parkge",
                    "parkge@gamil.com",
                    "1234"
            );
            createFollower(
                    targetUser.username(),
                    myUser.uuid()
            );

            mockMvc.perform(get("/api/profiles/gunKim"))
                    .andExpect(jsonPath("$.profile.username").value(targetUser.username()))
                    .andExpect(jsonPath("$.profile.bio").isEmpty())
                    .andExpect(jsonPath("$.profile.image").isEmpty())
                    .andExpect(jsonPath("$.profile.following").value(false))
                    .andDo(print());
        }
    }
}