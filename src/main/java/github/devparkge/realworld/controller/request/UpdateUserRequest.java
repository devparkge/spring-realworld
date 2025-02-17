package github.devparkge.realworld.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record UpdateUserRequest(
        String email,
        String username,
        String password,
        String bio,
        String image
) {
}
