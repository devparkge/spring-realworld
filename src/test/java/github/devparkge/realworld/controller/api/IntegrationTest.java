package github.devparkge.realworld.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.devparkge.realworld.domain.model.User;
import github.devparkge.realworld.domain.repository.InMemoryUserRepository;
import github.devparkge.realworld.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private InMemoryUserRepository userRepository;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected JwtUtil jwtUtil;

    @AfterEach
    void after() {
        this.userRepository.clear();
    }

    protected User createUser(String username, String email, String password) {
        return this.userRepository.saveUser(
                "parkge",
                "parkge@gmail.com",
                "1234"
        );
    }
}
