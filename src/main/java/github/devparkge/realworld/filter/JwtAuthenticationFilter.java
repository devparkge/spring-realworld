package github.devparkge.realworld.filter;


import github.devparkge.realworld.exception.InvalidTokenException;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


public class JwtAuthenticationFilter implements Filter {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final String header;
    private final String tokenPrefix;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserService userService, String header, String tokenPrefix) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.header = header;
        this.tokenPrefix = tokenPrefix;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if ((request instanceof HttpServletRequest httpRequest)) {
            doFilter(request, response, chain, httpRequest);
        }
        chain.doFilter(request, response);
    }

    private void doFilter(ServletRequest request, ServletResponse response, FilterChain chain, HttpServletRequest httpRequest) throws IOException, ServletException {
        String token = getToken(httpRequest)
                .orElse(null);
        if (token == null) {
            chain.doFilter(request, response);
        } else {
            UUID uuid = UUID.fromString(jwtUtil.parseToken(token));
            validateUUID(uuid);
            request.setAttribute("UUID", uuid);

            chain.doFilter(request, response);
        }
    }

    private Optional<String> getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(this.header))
                .filter(header -> header.startsWith(this.tokenPrefix))
                .map(header -> header.substring(7));
    }

    private void validateUUID(UUID uuid) {
        if (!userService.jwtAuthenticationByUUID(uuid)){
            throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        }
    }
}
