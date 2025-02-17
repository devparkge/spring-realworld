package github.devparkge.realworld.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record UpdateUserRequest(
        Optional<String> email,
        Optional<String> username,
        Optional<String> password,
        Optional<String> bio,
        Optional<String> image
) {
}
