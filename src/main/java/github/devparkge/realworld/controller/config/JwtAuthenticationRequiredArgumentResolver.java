package github.devparkge.realworld.controller.config;

import github.devparkge.realworld.controller.config.annotation.JwtAuthenticationOptional;
import github.devparkge.realworld.controller.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.exception.InvalidTokenException;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JwtAuthenticationRequiredArgumentResolver extends JwtAuthenticationOptionalArgumentResolver {
    public JwtAuthenticationRequiredArgumentResolver(UserService userService, JwtUtil jwtUtil, String header, String tokenPrefix) {
        super(userService, jwtUtil, header, tokenPrefix);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JwtAuthenticationRequired.class) != null;
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
                .orElseThrow(() -> new InvalidTokenException("유효하지 않은 토큰 입니다."));

        return validateToken(token);
    }
}
