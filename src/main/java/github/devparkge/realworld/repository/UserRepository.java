package github.devparkge.realworld.repository;

import github.devparkge.realworld.service.dto.IsvlidPasswordDto;

public interface UserRepository {
    //Login email 검증
    boolean isValidEmail(String email);

    //Login password 확인
    IsvlidPasswordDto isValidPassword(String email, String password);
}