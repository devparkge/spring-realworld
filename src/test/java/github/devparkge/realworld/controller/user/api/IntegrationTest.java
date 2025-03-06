package github.devparkge.realworld.controller.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.comment.model.Comment;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import github.devparkge.realworld.infrastructure.article.repository.InMemoryArticleRepository;
import github.devparkge.realworld.infrastructure.article.repository.InMemoryTagReadRepository;
import github.devparkge.realworld.infrastructure.comment.repository.InMemoryCommentRepository;
import github.devparkge.realworld.infrastructure.user.repository.UserRepositoryImpl;
import github.devparkge.realworld.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private InMemoryArticleRepository articleRepository;
    @Autowired
    private InMemoryTagReadRepository tagReadRepository;
    @Autowired
    private InMemoryCommentRepository commentRepository;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected JwtUtil jwtUtil;

    @AfterEach
    void after() {
        this.articleRepository.clear();
        this.tagReadRepository.clear();
        this.commentRepository.clear();
    }

    protected User createUser(String username, String email, String password) {
        return this.userRepository.saveUser(
                User.signUp(
                        UUID.randomUUID(),
                        username,
                        password,
                        email
                )
        );
    }

    protected void follow(UUID followerId, UUID followeeId) {
        this.userRepository.follow(
                followerId,
                followeeId
        );
    }

    protected Article createArticle(UUID userId, String title, String description, String body, List<String> tagList) {
        return this.articleRepository.save(
                Article.create(
                        userRepository.findByUUID(userId).orElseThrow(() -> new UUIDNotFoundException(String.format("%s를 찾을 수 없습니다.", userId))),
                        title,
                        description,
                        body,
                        tagList
                )
        );
    }

    protected Comment addComment(User user, Article article, String body) {
        return this.commentRepository.save(
                Comment.create(
                        user,
                        article,
                        body
                )
        );
    }

    protected void favorite(Article article, User userId) {
        this.articleRepository.favorite(
                article,
                userId
        );
    }
}
