package github.devparkge.realworld.controller.article.api;


import github.devparkge.realworld.controller.api.IntegrationTest;
import github.devparkge.realworld.controller.article.model.request.CreateArticleRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

@DisplayName("TagApiController 통합 테스트")
class TagApiControllerTest extends IntegrationTest {
    @Nested
    @DisplayName("GET /api/tags")
    class GetTags {
        @Test
        @DisplayName("모든 Tag가 담긴 List를 반환한다")
        void getTags() throws Exception {
            var user = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            createArticle(
                    user.uuid(),
                    "Test1",
                    "test1 create article",
                    "test1 create article integration test",
                    List.of("test1", "integrationTest", "integration")
            );

            mockMvc.perform(get("/api/tags"))
                    .andExpect(jsonPath("$.tags").isArray())
                    .andExpect(jsonPath("$.tags[0]").value("test1"))
                    .andExpect(jsonPath("$.tags[1]").value("integrationTest"))
                    .andExpect(jsonPath("$.tags[2]").value("integration"))
                    .andDo(print());
        }
    }
}