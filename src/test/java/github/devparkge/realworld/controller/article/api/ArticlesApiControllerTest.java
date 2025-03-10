package github.devparkge.realworld.controller.article.api;

import github.devparkge.realworld.controller.IntegrationTest;
import github.devparkge.realworld.controller.article.model.request.CreateArticleRequest;
import github.devparkge.realworld.controller.article.model.request.GetFeedArticlesRequest;
import github.devparkge.realworld.controller.article.model.request.UpdateArticleRequest;
import github.devparkge.realworld.domain.article.model.Slug;
import github.devparkge.realworld.domain.article.model.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
            String token = "Token " + jwtUtil.generateToken(user.uuid());

            mockMvc.perform(post("/api/articles")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("article", request))))
                    .andExpect(jsonPath("$.article.slug").value(Slug.from(request.title()).value()))
                    .andExpect(jsonPath("$.article.title").value(request.title()))
                    .andExpect(jsonPath("$.article.description").value(request.description()))
                    .andExpect(jsonPath("$.article.body").value(request.body()))
                    .andExpect(jsonPath("$.article.tagList").isArray())
                    .andExpect(jsonPath("$.article.tagList[0]").value("integration"))
                    .andExpect(jsonPath("$.article.tagList[1]").value("integrationTest"))
                    .andExpect(jsonPath("$.article.tagList[2]").value("test"))
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
    @DisplayName("GET /api/articles/:slug")
    class GetArticle {
        @Test
        @DisplayName("slug가 일치하는 Article을 반환한다.")
        void test() throws Exception {
            var user = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var article = createArticle(
                    user.uuid(),
                    "Test1",
                    "test1 create article",
                    "test1 create article integration test",
                    Stream.of("test1", "integrationTest", "integration").map(Tag::create).toList()
            );

            mockMvc.perform(get("/api/articles/test1"))
                    .andExpect(status().isOk())

                    .andExpect(jsonPath("$.article.slug").value(article.slug().value()))
                    .andExpect(jsonPath("$.article.title").value(article.title()))
                    .andExpect(jsonPath("$.article.description").value(article.description()))
                    .andExpect(jsonPath("$.article.body").value(article.body()))

                    .andExpect(jsonPath("$.article.tagList").isArray())

                    .andExpect(jsonPath("$.article.tagList[0]").value("integration"))
                    .andExpect(jsonPath("$.article.tagList[1]").value("integrationTest"))
                    .andExpect(jsonPath("$.article.tagList[2]").value("test1"))

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
                    Stream.of("dragons", "training").map(Tag::create).toList()
            );

            String token = "Token " + jwtUtil.generateToken(user.uuid());
            mockMvc.perform(get("/api/articles")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .param("tag", "dragons")
                            .param("author", "parkge"))
                    .andExpect(jsonPath("$.articles").isArray())
                    .andExpect(jsonPath("$.articlesCount").value(1))

                    .andExpect(jsonPath("$.articles[0].slug").value("test1"))
                    .andExpect(jsonPath("$.articles[0].title").value(article1.title()))
                    .andExpect(jsonPath("$.articles[0].description").value(article1.description()))
                    .andExpect(jsonPath("$.articles[0].body").value(article1.body()))
                    .andExpect(jsonPath("$.articles[0].tagList").isArray())
                    .andExpect(jsonPath("$.articles[0].tagList[0]").value("dragons"))
                    .andExpect(jsonPath("$.articles[0].tagList[1]").value("training"))
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
    @DisplayName("GET /api/articles/feed")
    class GetFeedArticles {
        @Test
        @DisplayName("follow한 유저의 arlticles를 반환한다.")
        void getFeedArticles() throws Exception {
            var followerUser = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var followeeUser = createUser(
                    "gunKim",
                    "gunKim@gmail.com",
                    "1234"
            );

            var article = createArticle(
                    followeeUser.uuid(),
                    "Test1",
                    "test1 create article",
                    "test1 create article integration test",
                    Stream.of("test1", "integrationTest", "integration").map(Tag::create).toList()
            );

            var request = new GetFeedArticlesRequest(
                    null,
                    null
            );

            follow(followeeUser.uuid(), followerUser.uuid());
            String token = "Token " + jwtUtil.generateToken(followerUser.uuid());

            mockMvc.perform(get("/api/articles/feed")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.articles").isArray())
                    .andExpect(jsonPath("$.articlesCount").value(1))

                    .andExpect(jsonPath("$.articles[0].slug").value("test1"))
                    .andExpect(jsonPath("$.articles[0].title").value(article.title()))
                    .andExpect(jsonPath("$.articles[0].description").value(article.description()))
                    .andExpect(jsonPath("$.articles[0].body").value(article.body()))
                    .andExpect(jsonPath("$.articles[0].tagList").isArray())
                    .andExpect(jsonPath("$.articles[0].tagList[0]").value("integration"))
                    .andExpect(jsonPath("$.articles[0].tagList[1]").value("integrationTest"))
                    .andExpect(jsonPath("$.articles[0].tagList[2]").value("test1"))
                    .andExpect(jsonPath("$.articles[0].createdAt").isNotEmpty())
                    .andExpect(jsonPath("$.articles[0].updatedAt").isNotEmpty())
                    .andExpect(jsonPath("$.articles[0].favorited").value(false))
                    .andExpect(jsonPath("$.articles[0].favoritesCount").value(0))

                    .andExpect(jsonPath("$.articles[0].author.username").value(followeeUser.username()))
                    .andExpect(jsonPath("$.articles[0].author.bio").value(followeeUser.bio()))
                    .andExpect(jsonPath("$.articles[0].author.image").value(followeeUser.image()))
                    .andExpect(jsonPath("$.articles[0].author.isFollowing").value(true))
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
            var article = ArticlesApiControllerTest.this.createArticle(
                    user.uuid(),
                    "Test1",
                    "test1 create article",
                    "test1 create article integration test",
                    Stream.of("test1", "integrationTest", "integration").map(Tag::create).toList()
            );

            var request = new UpdateArticleRequest(
                    "Test2",
                    "test2 create article",
                    "test2 create article integration test"
            );

            String token = "Token " + jwtUtil.generateToken(user.uuid());
            mockMvc.perform(put("/api/articles/test1")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("article", request))))
                    .andExpect(jsonPath("$.article.slug").value(Slug.from(request.title()).value()))
                    .andExpect(jsonPath("$.article.title").value(request.title()))
                    .andExpect(jsonPath("$.article.description").value(request.description()))
                    .andExpect(jsonPath("$.article.body").value(request.body()))
                    .andExpect(jsonPath("$.article.tagList").isArray())
                    .andExpect(jsonPath("$.article.tagList[0]").value("integration"))
                    .andExpect(jsonPath("$.article.tagList[1]").value("integrationTest"))
                    .andExpect(jsonPath("$.article.tagList[2]").value("test1"))
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
    @DisplayName("DELETE /api/articles/:slug")
    class DeleteArticle {
        @Test
        @DisplayName("인증된 유저의 slug가 일치하는 Article을 삭제한다.")
        void test() throws Exception {
            var user = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            ArticlesApiControllerTest.this.createArticle(
                    user.uuid(),
                    "Test1",
                    "test1 create article",
                    "test1 create article integration test",
                    Stream.of("test1", "integrationTest", "integration").map(Tag::create).toList()
            );

            String token = "Token " + jwtUtil.generateToken(user.uuid());
            mockMvc.perform(delete("/api/articles/test1")
                            .header(HttpHeaders.AUTHORIZATION, token))
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }
    @Nested
    @DisplayName("POST /api/articles/:slug/favorite")
    class FavoriteArticle {
        @Test
        @DisplayName("인증된 유저의 slug가 일치하는 Article을 favorite하고 반환한다.")
        void test() throws Exception {
            var ownerUser = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var targetUser = createUser(
                    "gunKim",
                    "gunKim@gmail.com",
                    "1234"
            );
            var article = createArticle(
                    targetUser.uuid(),
                    "Test1",
                    "test1 create article",
                    "test1 create article integration test",
                    Stream.of("test1", "integrationTest", "integration").map(Tag::create).toList()
            );

            String token = "Token " + jwtUtil.generateToken(ownerUser.uuid());
            mockMvc.perform(post("/api/articles/test1/favorite")
                            .header(HttpHeaders.AUTHORIZATION, token))
                    .andExpect(status().isOk())

                    .andExpect(jsonPath("$.article.slug").value(article.slug().value()))
                    .andExpect(jsonPath("$.article.title").value(article.title()))
                    .andExpect(jsonPath("$.article.description").value(article.description()))
                    .andExpect(jsonPath("$.article.body").value(article.body()))

                    .andExpect(jsonPath("$.article.tagList").isArray())
                    .andExpect(jsonPath("$.article.tagList[0]").value("integration"))
                    .andExpect(jsonPath("$.article.tagList[1]").value("integrationTest"))
                    .andExpect(jsonPath("$.article.tagList[2]").value("test1"))

                    .andExpect(jsonPath("$.article.createdAt").isNotEmpty())
                    .andExpect(jsonPath("$.article.updatedAt").isNotEmpty())
                    .andExpect(jsonPath("$.article.favorited").value(true))
                    .andExpect(jsonPath("$.article.favoritesCount").value(1))

                    .andExpect(jsonPath("$.article.author.username").value(targetUser.username()))
                    .andExpect(jsonPath("$.article.author.bio").value(targetUser.bio()))
                    .andExpect(jsonPath("$.article.author.image").value(targetUser.image()))
                    .andExpect(jsonPath("$.article.author.isFollowing").value(false))

                    .andDo(print());
        }
    }
    @Nested
    @DisplayName("DELETE /api/articles/:slug/favorite")
    class UnFavoriteArticle {
        @Test
        @DisplayName("인증된 유저의 slug가 일치하는 Article을 unFavorite하고 반환한다.")
        void test() throws Exception {
            var ownerUser = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var targetUser = createUser(
                    "gunKim",
                    "gunKim@gmail.com",
                    "1234"
            );
            var article = createArticle(
                    targetUser.uuid(),
                    "Test1",
                    "test1 create article",
                    "test1 create article integration test",
                    Stream.of("test1", "integrationTest", "integration").map(Tag::create).toList()
            );
            favorite(article, ownerUser);
            String token = "Token " + jwtUtil.generateToken(ownerUser.uuid());

            mockMvc.perform(delete("/api/articles/test1/favorite")
                            .header(HttpHeaders.AUTHORIZATION, token))
                    .andExpect(status().isOk())

                    .andExpect(jsonPath("$.article.slug").value(article.slug().value()))
                    .andExpect(jsonPath("$.article.title").value(article.title()))
                    .andExpect(jsonPath("$.article.description").value(article.description()))
                    .andExpect(jsonPath("$.article.body").value(article.body()))

                    .andExpect(jsonPath("$.article.tagList").isArray())
                    .andExpect(jsonPath("$.article.tagList[0]").value("integration"))
                    .andExpect(jsonPath("$.article.tagList[1]").value("integrationTest"))
                    .andExpect(jsonPath("$.article.tagList[2]").value("test1"))

                    .andExpect(jsonPath("$.article.createdAt").isNotEmpty())
                    .andExpect(jsonPath("$.article.updatedAt").isNotEmpty())
                    .andExpect(jsonPath("$.article.favorited").value(false))
                    .andExpect(jsonPath("$.article.favoritesCount").value(0))

                    .andExpect(jsonPath("$.article.author.username").value(targetUser.username()))
                    .andExpect(jsonPath("$.article.author.bio").value(targetUser.bio()))
                    .andExpect(jsonPath("$.article.author.image").value(targetUser.image()))
                    .andExpect(jsonPath("$.article.author.isFollowing").value(false))

                    .andDo(print());
        }
    }
}