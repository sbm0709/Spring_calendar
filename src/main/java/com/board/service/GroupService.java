package com.board.service;


import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.board.mappers.GroupMapper;
import com.board.mappers.UserMapper;

@Service
@RequiredArgsConstructor
@Log4j2
public class GroupService {

    private final GroupMapper groupMapper;


    // 그룹 생성하기
    public void group_create(GroupDTO groupDTO, UserDTO userDTO){

        log.warn("group_create 시작");
        groupDTO.setCreateIdNo(userDTO.getIdNo());
        groupMapper.group_create(groupDTO);

        log.warn("group_create 성공");

        groupMapper.user_group_insert(groupDTO);
        log.warn("user_group_insert 성공");
    }




}
