package com.board.service;


import com.board.dto.User;
import com.board.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserMapper userMapper;

    public User selectUser(User user){
        return userMapper.user_Select(user);
    }

}
