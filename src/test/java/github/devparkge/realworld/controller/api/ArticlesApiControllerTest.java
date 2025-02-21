package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.controller.request.CreateArticleRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@DisplayName("ArticlesApiController 통합 테스트")
class ArticlesApiControllerTest extends IntegrationTest {
    @Nested
    @DisplayName("POST /api/articles")
    class CreateArticle {
        @Test
        @DisplayName("인증된 유저 요청에 대한 article을 반환한다.")
        void createArticle() throws Exception {
            var user = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var request = new CreateArticleRequest(
                    "Test",
                    "test create article",
                    "test create article integration test",
                    List.of("test", "integrationTest", "integration")
            );
            String token = "Bearer " + jwtUtil.generateToken(user.uuid());

            mockMvc.perform(post("/api/articles")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("article", request))))
                    .andExpect(jsonPath("$.article.slug").value(request.title().toLowerCase()))
                    .andExpect(jsonPath("$.article.title").value(request.title()))
                    .andExpect(jsonPath("$.article.description").value(request.description()))
                    .andExpect(jsonPath("$.article.body").value(request.body()))
                    .andExpect(jsonPath("$.article.tagList").isArray())
                    .andExpect(jsonPath("$.article.tagList[0]").value("test"))
                    .andExpect(jsonPath("$.article.tagList[1]").value("integrationTest"))
                    .andExpect(jsonPath("$.article.tagList[2]").value("integration"))
                    .andExpect(jsonPath("$.article.createdAt").isNotEmpty())
                    .andExpect(jsonPath("$.article.updatedAt").isNotEmpty())
                    .andExpect(jsonPath("$.article.favorited").value(false))
                    .andExpect(jsonPath("$.article.favoritesCount").value(0))
                    .andExpect(jsonPath("$.article.author.username").value(user.username()))
                    .andExpect(jsonPath("$.article.author.bio").value(user.bio()))
                    .andExpect(jsonPath("$.article.author.image").value(user.image()))
                    .andExpect(jsonPath("$.article.author.isFollowing").value(false))
                    .andDo(print());
        }
    }

}