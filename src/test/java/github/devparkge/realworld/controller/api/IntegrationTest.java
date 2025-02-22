package github.devparkge.realworld.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.devparkge.realworld.domain.article.model.Article;
import github.devparkge.realworld.domain.article.repository.InMemoryArticleRepository;
import github.devparkge.realworld.domain.user.model.User;
import github.devparkge.realworld.domain.user.repository.InMemoryFollowerRepository;
import github.devparkge.realworld.domain.user.repository.InMemoryUserRepository;
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
    private InMemoryUserRepository userRepository;
    @Autowired
    private InMemoryFollowerRepository followerRepository;
    @Autowired
    private InMemoryArticleRepository articleRepository;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected JwtUtil jwtUtil;

    @AfterEach
    void after() {
        this.userRepository.clear();
        this.followerRepository.clear();
        this.articleRepository.clear();
    }

    protected User createUser(String username, String email, String password) {
        return this.userRepository.saveUser(
                username,
                email,
                password
        );
    }

    protected void createFollower(String username, UUID uuid) {
        this.followerRepository.follow(
                username,
                uuid
        );
    }

    protected Article createArticle(UUID userId, String title, String description, String body, List<String> tagList) {
        return this.articleRepository.saveArticle(
                userId,
                title,
                description,
                body,
                tagList
        );
    }
}
