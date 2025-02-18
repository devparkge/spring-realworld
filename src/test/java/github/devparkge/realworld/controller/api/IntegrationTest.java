package github.devparkge.realworld.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.devparkge.realworld.domain.model.Follower;
import github.devparkge.realworld.domain.model.User;
import github.devparkge.realworld.domain.repository.InMemoryFollowerRepository;
import github.devparkge.realworld.domain.repository.InMemoryUserRepository;
import github.devparkge.realworld.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private InMemoryUserRepository userRepository;
    @Autowired
    private InMemoryFollowerRepository followerRepository;
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
    }

    protected User createUser(String username, String email, String password) {
        return this.userRepository.saveUser(
                username,
                email,
                password
        );
    }

    protected Follower createFollower(String username, UUID uuid) {
        return this.followerRepository.follow(
                username,
                uuid
        );
    }
}
