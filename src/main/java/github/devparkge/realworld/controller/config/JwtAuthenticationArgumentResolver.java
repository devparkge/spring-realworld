package github.devparkge.realworld.controller.config;

import github.devparkge.realworld.controller.config.annotation.JwtAuthentication;
import github.devparkge.realworld.exception.InvalidTokenException;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
public class JwtAuthenticationArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final String header;
    private final String tokenPrefix;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JwtAuthentication.class) != null;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = extractToken(httpServletRequest)
                .orElseThrow(() -> new IllegalArgumentException("토큰이 존재하지 않습니다."));

        return validateToken(token);
    }

    private UUID validateToken(String token) {
        UUID uuid = jwtUtil.parseToken(token);
        validateUUID(uuid);
        return uuid;
    }

    private void validateUUID(UUID uuid) {
        if(!userService.jwtAuthenticationByUUID(uuid)) {
            throw new InvalidTokenException("존재하지 않는 토큰입니다.");
        }
    }

    private Optional<String> extractToken(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(header))
                .filter(header -> header.startsWith(tokenPrefix))
                .map(header -> header.substring(7));
    }
}
