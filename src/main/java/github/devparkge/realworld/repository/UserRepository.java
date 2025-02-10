package github.devparkge.realworld.repository;

import github.devparkge.realworld.service.dto.SelectUserToPasswordDto;

public interface UserRepository {
    //Login email 검증
    boolean findEmail(String email);

    //Login password 확인
    SelectUserToPasswordDto getUserToEmail(String email, String password);
}