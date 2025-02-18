package github.devparkge.realworld.controller.config;

import github.devparkge.realworld.controller.config.annotation.JwtAuthenticationRequired;
import github.devparkge.realworld.exception.UUIDNotFoundException;
import github.devparkge.realworld.service.UserService;
import github.devparkge.realworld.util.JwtUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;
import java.util.UUID;

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
        return Optional.ofNullable((UUID) super.resolveArgument(parameter, mavContainer, webRequest, binderFactory))
                .orElseThrow(() -> new UUIDNotFoundException("메시지"));
    }
}
