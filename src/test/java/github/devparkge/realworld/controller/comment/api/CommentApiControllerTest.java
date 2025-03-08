package github.devparkge.realworld.controller.comment.api;

import github.devparkge.realworld.controller.IntegrationTest;
import github.devparkge.realworld.controller.comment.model.request.AddCommentRequest;
import github.devparkge.realworld.domain.article.model.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("CommentApiController 통합 테스트")
class CommentApiControllerTest extends IntegrationTest {
    @Nested
    @DisplayName("POST /api/articles/:slug/comments")
    class AddComment {
        @Test
        @DisplayName("인증된 사용자의 요청에 comment를 반환한다.")
        void addComment() throws Exception {
            var requestUser = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var articleUser = createUser(
                    "gunKim",
                    "gunKim@gmail.com",
                    "1234"
            );
            createArticle(
                    articleUser.uuid(),
                    "TEST",
                    "test description",
                    "test body",
                    Stream.of("test", "integrationTest").map(Tag::create).toList()
            );
            var request = new AddCommentRequest(
                    "add comment body"
            );
            String token = "Token " + jwtUtil.generateToken(requestUser.uuid());

            mockMvc.perform(post("/api/articles/test/comments")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("comment", request))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.comment.id").isNotEmpty())
                    .andExpect(jsonPath("$.comment.createdAt").isNotEmpty())
                    .andExpect(jsonPath("$.comment.updatedAt").isNotEmpty())
                    .andExpect(jsonPath("$.comment.body").value(request.body()))

                    .andExpect(jsonPath("$.comment.author.username").value(requestUser.username()))
                    .andExpect(jsonPath("$.comment.author.bio").value(requestUser.bio()))
                    .andExpect(jsonPath("$.comment.author.image").value(requestUser.image()))
                    .andExpect(jsonPath("$.comment.author.isFollowing").value(false))

                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("GET /api/articles/:slug/comments")
    class GetComments {
        @Test
        @DisplayName("slug가 존재하는 article의  comments를 반환한다.")
        void getComments() throws Exception {
            var requestUser = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var articleUser = createUser(
                    "gunKim",
                    "gunKim@gmail.com",
                    "1234"
            );
            var article = createArticle(
                    articleUser.uuid(),
                    "TEST",
                    "test description",
                    "test body",
                    Stream.of("test", "integrationTest").map(Tag::create).toList()
            );
            addComment(
                    requestUser,
                    article,
                    "comment Body"
            );
            String token = "Token " + jwtUtil.generateToken(requestUser.uuid());

            mockMvc.perform(get("/api/articles/test/comments")
                            .header(HttpHeaders.AUTHORIZATION, token))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.comments").isArray())
                    .andExpect(jsonPath("$.comments[0].id").isNotEmpty())
                    .andExpect(jsonPath("$.comments[0].createdAt").isNotEmpty())
                    .andExpect(jsonPath("$.comments[0].updatedAt").isNotEmpty())
                    .andExpect(jsonPath("$.comments[0].body").value("comment Body"))

                    .andExpect(jsonPath("$.comments[0].author.username").value(requestUser.username()))
                    .andExpect(jsonPath("$.comments[0].author.bio").value(requestUser.bio()))
                    .andExpect(jsonPath("$.comments[0].author.image").value(requestUser.image()))
                    .andExpect(jsonPath("$.comments[0].author.isFollowing").value(false))

                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("DELETE /api/articles/:slug/comments/:id")
    class DeleteComment {
        @Test
        @DisplayName("slug가 존재하는 article의 id에 해당하는 comment를 삭제한다.")
        void getComments() throws Exception {
            var requestUser = createUser(
                    "parkge",
                    "parkge@gmail.com",
                    "1234"
            );
            var articleUser = createUser(
                    "gunKim",
                    "gunKim@gmail.com",
                    "1234"
            );
            var article = createArticle(
                    articleUser.uuid(),
                    "TEST",
                    "test description",
                    "test body",
                    Stream.of("test", "integrationTest").map(Tag::create).toList()
            );
            addComment(
                    requestUser,
                    article,
                    "comment Body"
            );
            String token = "Token " + jwtUtil.generateToken(requestUser.uuid());

            mockMvc.perform(delete("/api/articles/test/comments/1")
                            .header(HttpHeaders.AUTHORIZATION, token))
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }
}