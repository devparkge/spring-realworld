package github.devparkge.realworld.controller.api;

import github.devparkge.realworld.controller.article.model.request.CreateArticleRequest;
import github.devparkge.realworld.controller.article.model.request.GetArticlesRequest;
import github.devparkge.realworld.controller.article.model.request.UpdateArticleRequest;
import github.devparkge.realworld.domain.article.model.Slug;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                    .andExpect(jsonPath("$.article.slug").value(Slug.from(request.title()).value()))
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

    @Nested
    @DisplayName("GET /api/articles")
    class GetArticles {
        @Test
        @DisplayName("tag쿼리 파라미터만 존재할 경우 arlticles를 반환한다.")
        void getArticlesByTag() throws Exception {
            var user = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var article1 = createArticle(
                    user.uuid(),
                    "Test1",
                    "test1 create article",
                    "test1 create article integration test",
                    List.of("test1", "integrationTest", "integration")
            );

            String token = "Bearer " + jwtUtil.generateToken(user.uuid());
            mockMvc.perform(get("/api/articles")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .param("tag", "integrationTest")
                            .param("author", "parkge"))
                    .andExpect(jsonPath("$.articles").isArray())
                    .andExpect(jsonPath("$.articlesCount").value(1))

                    .andExpect(jsonPath("$.articles[0].slug").value("test1"))
                    .andExpect(jsonPath("$.articles[0].title").value(article1.title()))
                    .andExpect(jsonPath("$.articles[0].description").value(article1.description()))
                    .andExpect(jsonPath("$.articles[0].body").value(article1.body()))
                    .andExpect(jsonPath("$.articles[0].tagList").isArray())
                    .andExpect(jsonPath("$.articles[0].tagList[0]").value("test1"))
                    .andExpect(jsonPath("$.articles[0].tagList[1]").value("integrationTest"))
                    .andExpect(jsonPath("$.articles[0].tagList[2]").value("integration"))
                    .andExpect(jsonPath("$.articles[0].createdAt").isNotEmpty())
                    .andExpect(jsonPath("$.articles[0].updatedAt").isNotEmpty())
                    .andExpect(jsonPath("$.articles[0].favorited").value(false))
                    .andExpect(jsonPath("$.articles[0].favoritesCount").value(0))
                    .andExpect(jsonPath("$.articles[0].author.username").value(user.username()))
                    .andExpect(jsonPath("$.articles[0].author.bio").value(user.bio()))
                    .andExpect(jsonPath("$.articles[0].author.image").value(user.image()))
                    .andExpect(jsonPath("$.articles[0].author.isFollowing").value(false))
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("PUT /api/articles/:slug")
    class UpdateArticle {
        @Test
        @DisplayName("인증된 유저의 업데이트된 Article을 반환한다.")
        void test() throws Exception {
            var user = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var article1 = ArticlesApiControllerTest.this.createArticle(
                    user.uuid(),
                    "Test1",
                    "test1 create article",
                    "test1 create article integration test",
                    List.of("test1", "integrationTest", "integration")
            );

            var request = new UpdateArticleRequest(
                    "Test2",
                    "test2 create article",
                    "test2 create article integration test"
            );

            String token = "Bearer " + jwtUtil.generateToken(user.uuid());
            mockMvc.perform(put("/api/articles/test1")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("article", request))))
                    .andExpect(jsonPath("$.article.slug").value(Slug.from(request.title()).value()))
                    .andExpect(jsonPath("$.article.title").value(request.title()))
                    .andExpect(jsonPath("$.article.description").value(request.description()))
                    .andExpect(jsonPath("$.article.body").value(request.body()))
                    .andExpect(jsonPath("$.article.tagList").isArray())
                    .andExpect(jsonPath("$.article.tagList[0]").value("test1"))
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