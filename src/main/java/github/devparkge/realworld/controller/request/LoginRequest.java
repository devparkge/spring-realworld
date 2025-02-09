package github.devparkge.realworld.controller.request;

import github.devparkge.realworld.controller.dto.UserByLoginRequest;

public record LoginRequest(
    UserByLoginRequest user
){
}
