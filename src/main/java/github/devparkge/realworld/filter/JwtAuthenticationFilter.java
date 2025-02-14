package github.devparkge.realworld.filter;


import github.devparkge.realworld.exception.InvalidTokenException;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
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
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) chain.doFilter(request, response);
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = getToken(httpRequest);
        if (token == null) {
            chain.doFilter(request, response);
        } else {
            UUID uuid = UUID.fromString(jwtUtil.parseToken(token));
            validateUUID(uuid);
            request.setAttribute("UUID", uuid);

            chain.doFilter(request, response);
        }
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(this.header);
        return (header != null && header.startsWith(this.tokenPrefix)) ? header.substring(7) : null;
    }

    private void validateUUID(UUID uuid) {
        if (!userService.jwtAuthenticationByUUID(uuid)){
            throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
