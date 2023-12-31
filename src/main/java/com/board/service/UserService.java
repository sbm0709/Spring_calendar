package com.board.service;


import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.dto.UserGroupDTO;
import com.board.mappers.UserMapper;
import com.board.mappers.GroupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserMapper userMapper;
    private final GroupMapper groupMapper;

    public UserDTO user_login(UserDTO userDTO){
        return userMapper.user_login(userDTO);
    }


    public void user_register(UserDTO userDTO, MultipartFile fileData) {
        try {
            byte[] data = fileData.getBytes();
            userMapper.user_register(userDTO, data);
        }catch (Exception e){
            log.info(e);
        }
    }

    public UserDTO selectUser(String id){
        return userMapper.user_select(id);
    }

    public void user_change_profile(byte[] data,int idNo){
        userMapper.change_profile(data, idNo);
    }


    // 현재 참여중인 그룹no들
    public List<Integer> user_belong_groupNo(UserDTO userDTO){
        return groupMapper.user_belong_group_select(userDTO);
    }


    // 그룹 초대 하기
    public void group_invite_user(String id, int groupNo){

        if(userMapper.user_select(id) == null){
            log.warn("group_invite_user - 유저없음!");
            return;
        }

        UserDTO user = userMapper.user_select(id);
        UserGroupDTO userGroupDTO  = new UserGroupDTO(user.getIdNo(), groupNo);

        if(userMapper.user_group_select(userGroupDTO) != null){
            log.warn("group_invite_user 실패");
            return;
        }

        userMapper.user_invite(userGroupDTO);
        log.warn("group_invite_user 성공");
    }


    //그룹 탈퇴 하기
    public void group_secession_user(int idNo, int groupNo){
        log.warn("group_secession_user idNo=" + idNo);
        log.warn("group_secession_user groupNo=" + groupNo);
        userMapper.user_group_secession(
                new UserGroupDTO(idNo,
                groupNo));
        log.warn("탈퇴 성공");
    }

    public void user_delete(String id){
        userMapper.user_delete(id);
    }
}
