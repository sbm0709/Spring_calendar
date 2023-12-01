package com.board.service;


import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserMapper userMapper;

    public UserDTO selectUser(UserDTO userDTO){
        return userMapper.user_select(userDTO);
    }

    void user_invite(UserDTO userDTO, GroupDTO groupDTO){
        userMapper.user_invite(userDTO, groupDTO);
    };

}
