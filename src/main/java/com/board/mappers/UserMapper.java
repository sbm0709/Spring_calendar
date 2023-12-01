package com.board.mappers;

import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 유저 회원가입
    void user_register(UserDTO userDTO);
    // 유저 로그인
    UserDTO user_login(UserDTO userDTO);


    UserDTO user_select(UserDTO userDTO);

    void user_invite(UserDTO userDTO, GroupDTO groupDTO);

}




