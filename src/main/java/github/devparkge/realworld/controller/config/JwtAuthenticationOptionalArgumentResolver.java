package github.devparkge.realworld.controller.config;

import github.devparkge.realworld.controller.config.annotation.JwtAuthenticationOptional;
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
public class JwtAuthenticationOptionalArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final String header;
    private final String tokenPrefix;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JwtAuthenticationOptional.class) != null;
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
                .orElse(null);

        return Optional.ofNullable(validateToken(token));
    }

    protected UUID validateToken(String token) {
        if(token == null) return null;
        UUID uuid = jwtUtil.parseToken(token);
        return validateUUID(uuid);
    }

    protected UUID validateUUID(UUID uuid) {
        return (!userService.jwtAuthenticationByUUID(uuid)) ? null : uuid;
    }

    protected Optional<String> extractToken(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(header))
                .filter(header -> header.startsWith(tokenPrefix))
                .map(header -> header.substring(7));
    }
}
