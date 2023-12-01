package com.board.service;


import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.dto.UserGroupDTO;
import com.board.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserMapper userMapper;

    public UserDTO user_login(UserDTO userDTO){
        return userMapper.user_login(userDTO);
    }


    public void user_register(UserDTO userDTO){ userMapper.user_register(userDTO); }

    public UserDTO user_select(String id){
        return userMapper.user_select(id);
    }


    // 그룹 초대 하기
    public void group_invite_user(String id, GroupDTO groupDTO){
        UserDTO user = userMapper.user_select(id);
        UserGroupDTO userGroupDTO  = new UserGroupDTO(user.getIdNo(), groupDTO.getGroupNo());

        if(userMapper.user_group_select(userGroupDTO) != null){
            log.warn("group_invite_user 실패");
            return;
        }

        userMapper.user_invite(userGroupDTO);
        log.warn("group_invite_user 성공");
    }


    //그룹 탈퇴 하기
    public void group_secession_user(String id, GroupDTO groupDTO){
        userMapper.user_group_secession(
                new UserGroupDTO(userMapper.user_select(id).getIdNo(),
                groupDTO.getGroupNo()));
    }
}
