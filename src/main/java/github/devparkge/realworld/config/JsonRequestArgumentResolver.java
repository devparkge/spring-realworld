package github.devparkge.realworld.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import github.devparkge.realworld.config.annotation.JsonRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonRequestArgumentResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper;

    public JsonRequestArgumentResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JsonRequest.class) != null;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        JsonRequest annotation = parameter.getParameterAnnotation(JsonRequest.class);
        if (annotation == null) {
            throw new IllegalArgumentException("No JsonRequest annotation present");
        }

        String subTreeKey = annotation.value();
        String body = getBody(webRequest);

        JsonNode node = objectMapper.readTree(body);
        JsonNode subTreeNode = node.get(subTreeKey);
        if (subTreeNode == null) {
            throw new IllegalArgumentException("JSON does not contain '" + subTreeKey + "' field");
        }

        return objectMapper.treeToValue(subTreeNode, parameter.getParameterType());
    }

    private String getBody(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (servletRequest == null) {
            throw new IllegalArgumentException("HttpServletRequest is missing");
        }

        try (BufferedReader reader = servletRequest.getReader()) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
    }
}