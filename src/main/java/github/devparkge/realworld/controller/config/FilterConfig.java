package github.devparkge.realworld.controller.config;

import github.devparkge.realworld.filter.JwtAuthenticationFilter;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class FilterConfig {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilter(
            @Value("${jwt.header}")
            String header,
            @Value("${jwt.token-prefix}")
            String tokenPrefix
    ) {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthenticationFilter(jwtUtil, userService, header, tokenPrefix));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
