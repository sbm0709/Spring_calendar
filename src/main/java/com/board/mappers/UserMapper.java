package com.board.mappers;

import com.board.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 유저 회원가입
    void user_register(User user);
    // 유저 로그인
    User user_login(User user);
}




