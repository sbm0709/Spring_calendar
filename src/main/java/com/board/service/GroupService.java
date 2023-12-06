package com.board.service;


import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.board.mappers.GroupMapper;
import com.board.mappers.UserMapper;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class GroupService {

    private final GroupMapper groupMapper;
    private final UserMapper userMapper;


    // 그룹 생성하기
    public void group_create(GroupDTO groupDTO, UserDTO userDTO){
        if(Objects.equals(groupDTO.getGroupName(), "null")) return;
        log.warn("group_create 시작");
        groupDTO.setCreateIdNo(userDTO.getIdNo());
        groupMapper.group_create(groupDTO);
        log.warn("group_create 성공");

        groupMapper.user_group_insert(groupDTO);
        log.warn("user_group_insert 성공");
    }

    public GroupDTO Select_user_group(int groupNo){return groupMapper.user_belong_groups_select(groupNo);}

    public void group_delete(int groupNo){groupMapper.group_delete(groupNo);}

    public int group_created_idNo_select(int groupNo){return groupMapper.group_created_idNo_select(groupNo);}

}
