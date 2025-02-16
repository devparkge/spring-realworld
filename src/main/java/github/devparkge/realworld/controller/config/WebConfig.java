package github.devparkge.realworld.controller.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final String header;
    private final String tokenPrefix;

    public WebConfig(
            ObjectMapper objectMapper,
            UserService userService,
            JwtUtil jwtUtil,
            @Value("${jwt.header}") String header,
            @Value("${jwt.token-prefix}") String tokenPrefix
    ) {
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.header = header;
        this.tokenPrefix = tokenPrefix;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new JsonRequestArgumentResolver(objectMapper));
        resolvers.add(new JwtAuthenticationArgumentResolver(
                userService,
                jwtUtil,
                header,
                tokenPrefix
        ));
    }
}
